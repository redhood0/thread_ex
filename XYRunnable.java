package test.thread;

public class XYRunnable implements Runnable {
    boolean flag = true;
    private static int x = 0;
    private final  int y = x++;
    public XYRunnable(){
        System.out.println(Thread.currentThread().getName() + y + "start");
    }

    @Override
    public void run() {
        System.out.println("1-----------"+Thread.currentThread().getName());
        Thread.yield();
        System.out.println("2-----------"+Thread.currentThread().getName());
        Thread.yield();
        System.out.println("3-----------"+Thread.currentThread().getName());
        Thread.yield();
        System.out.println("end"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        XYRunnable xyRunnable = new XYRunnable();
        for(int i = 5; i > 0 ; i--){
           // new Thread(xyRunnable).start();
            new Thread(new XYRunnable()).start();
        }


    }
}
