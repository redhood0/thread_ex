package test.thread.car_creat;



import java.util.HashSet;
import java.util.Set;

public class RobotPool {
    private Set<Robot> pool = new HashSet<>();
    public synchronized void add(Robot r){
        pool.add(r);
        notifyAll();
    }
    public synchronized void hire(Class<? extends  Robot> robotype, Assembler d) throws InterruptedException {
        for(Robot r : pool){
            if(r.getClass().equals(robotype)){
                pool.remove(r);
                r.assignAssembler(d);
                r.enagge();//power it up to do the work
                return;
            }
        }
        wait();//none available
        hire(robotype,d);//try again
    }

    public synchronized void release(Robot r){
        add(r);
    }
}
