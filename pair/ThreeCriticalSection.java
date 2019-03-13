package test.thread.pair;

import test.thread.test.thread.date.Ticket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreeCriticalSection {
    Object o1 = new Object();
    Object o2 = new Object();
    Object o3 = new Object();

    public void first(){
        synchronized (o3){
            for(int i = 0;i < 5; i++){
                System.out.println("first");
                Thread.yield();
                try {
                    TimeUnit.MICROSECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void second(){
        synchronized (o3){
            for(int i = 0;i < 5; i++){
                System.out.println("second");
                Thread.yield();
                try {
                    TimeUnit.MICROSECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void third(){
        synchronized (o3){
            for(int i = 0;i < 5; i++){
                System.out.println("third");
                Thread.yield();
                try {
                    TimeUnit.MICROSECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreeCriticalSection section = new ThreeCriticalSection();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                section.first();

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                section.second();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                section.third();
            }
        });


    }
}
