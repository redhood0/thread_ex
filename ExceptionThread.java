package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionThread implements Runnable{

    @Override
    public void run() {
        System.out.println("run by" + Thread.currentThread());
        throw new RuntimeException("我写的");
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ExceptionThread());
        //service.execute(new ExceptionThread());
    }
}

class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creat a new Thread");
        Thread thread = new Thread(r);
        System.out.println(" creat " + thread);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println(" eh= " + thread.getUncaughtExceptionHandler());
        return thread;

    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " is happen ： " + e);
    }
}
