package test.thread.car_creat;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//assembler 装配工
public class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQUeue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQUeue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQUeue = finishingQUeue;
        this.robotPool = robotPool;
    }

    public Car getCar() {
        return car;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Blocks until chassis is available
                car = chassisQueue.take();
                //Hire robots to perform work
                robotPool.hire(EngineRobot.class,this);
                robotPool.hire(DriveTrainRobot.class,this);
                robotPool.hire(WheelRobot.class,this);
                barrier.await();//until the robot s finish
                finishingQUeue.add(car);
            }
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
