package test.thread;

public class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("这里是一个非匿名Runnable");
        System.out.println("休眠ing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{

        }
        System.out.println("休眠end！");
    }

    public void start(){
        Thread thread = new Thread(this,"threadname" + this.toString());
        thread.start();
        System.out.println(thread.getName() + "---" + thread.getId() + "---" + thread.getState() + "--" + thread.getContextClassLoader()
            + "--" + thread.isAlive() + "--" + thread.getPriority());
    }

    public static void main(String[] args) {
        TestRunnable runnable = new TestRunnable();
        runnable.start();
        System.out.println("mian thread");

        Thread thread = new Thread(() -> System.out.println(1));
    }
}
