package test.thread.car_creat;

import java.util.concurrent.BrokenBarrierException;

public abstract class Robot implements Runnable{
    private RobotPool robotPool;
    public Robot(RobotPool p){ robotPool = p;}
    protected Assembler assembler;
    public Robot assignAssembler(Assembler assembler){
        this.assembler = assembler;
        return this;
    }
    //参与engage
    private boolean engage = false;
    public synchronized void enagge(){
        engage = true;
        notifyAll();
    }
    //关闭
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        robotPool.release(this);
        while (engage == false){
            wait();
        }
    }
    abstract protected void perfomService();
    @Override
    public void run(){
        try{
            powerDown();//wait until needed
            while (!Thread.interrupted()){
                perfomService();
                assembler.getBarrier().await();
                //we're done with that job
                powerDown();
            }
        }catch(InterruptedException e){
            System.out.println("Exiting" + this + "via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString(){
        return getClass().getName();
    }



}
