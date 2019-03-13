package test.thread;

public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;//任务数，静态变量
    private final int id = taskCount++;
    public  LiftOff(){
    }
    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#").append(id).append("(").append(countDown > 0 ? countDown : "liftoff!").append(")");
        return stringBuffer.toString();
    }

    @Override
    public void run() {
        while(countDown-- > 0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
