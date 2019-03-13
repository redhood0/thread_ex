package test.thread;


import java.util.concurrent.atomic.AtomicInteger;

public class CkyThread extends Thread {
    public CkyThread(String name) {
        super(name);
    }

    static int ticket = 0;
    static AtomicInteger atomicInteger = new AtomicInteger(20);

//    public void run() {
//        while (ticket > 0) {
//            synchronized (CkyThread.class) {
//                if (ticket > 0) {
//                    System.out.println(getName() + "卖出了第" + ticket + "张票");
//                    ticket--;
//                } else {
//                    System.out.println( getName() + "票卖完了");
//                }
//            }
//
//        }

        public void run() {
            while (true) {
                    if (atomicInteger.get() > 0) {
                        System.out.println(getName() + "卖出了第" + (atomicInteger.decrementAndGet()+1) + "号票");
                    } else {
                        System.out.println(getName() + "票卖完了");
                        return;
                    }

            }

    }

    public static void main(String[] args) throws InterruptedException {
        CkyThread station1 = new CkyThread("窗口1");
        CkyThread station2 = new CkyThread("窗口2");
        CkyThread station3 = new CkyThread("窗口3");

        station1.start();
        station2.start();
        station3.start();
        Thread.sleep(3000);
        System.out.println("公司剩余" + atomicInteger.get() + "票");
    }
}