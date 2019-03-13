package test.thread.interrupt_test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("interrupting  " + r.getClass().getName());
        boolean flag = f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName() + "--" + flag);
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());

        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System exit(0)");
        System.exit(0);
    }
}

class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("InterruptExection");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }


    @Override
    public void run() {
        System.out.println("Waiting for read()");
        try {
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupt from block I/O");
            } else {
                throw new RuntimeException(e);
            }
            e.printStackTrace();
        }
        System.out.println("Exiting IOBlocked.run");
    }
}

class SynchronizedBlocked implements Runnable {
    //这是一个锁的方法
    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked(){
        //构造函数中出现的匿名内部类,该类也想调用f，但是被阻塞住了。
        new Thread(){
            @Override
            public void run(){
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("try to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}
