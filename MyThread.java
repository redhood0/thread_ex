package test.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread{


    public static void main(String[] args) {
       Ticket ticket = new Ticket();
       Thread thread1 = new Thread(){
           @Override
           public void run() {
               synchronized (MyThread.class) {
                   while (ticket.get() > 0) {

                       ticket.buyOneTicket();
                       try {
                           sleep(100);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       System.out.println("票--" + ticket.get() + currentThread().getName());
                   }
               }
           }
       };



        Thread thread2 = new Thread(){
            @Override
            public void run(){
                synchronized (MyThread.class) {
                    while (ticket.get() > 0) {
                        ticket.buyOneTicket();
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("票--" + ticket.get() + currentThread().getName());
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}

