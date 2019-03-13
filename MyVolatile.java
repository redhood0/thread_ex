package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyVolatile implements Runnable{
    private static  boolean flag = true;

    public static void changeFlag(){
        MyVolatile.flag = false;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        flag  = true;
        System.out.println("in volatitle1");
        while(flag){
            System.out.println("flag is true");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new MyVolatile());
        executorService.submit(new MyVolatile2());
    }
}

class MyVolatile2 implements Runnable{
    @Override
    public void run() {
        System.out.println("flag will be change");
        MyVolatile.changeFlag();
    }
}