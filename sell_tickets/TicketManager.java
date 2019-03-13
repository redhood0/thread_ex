package test.thread.sell_tickets;

import java.util.concurrent.*;

public class TicketManager implements Runnable{
    private Ticket ticket;
    public volatile static boolean flag = false;

    //private static CountDownLatch cdl = new CountDownLatch(10);

    public  TicketManager(Ticket t) {
        ticket = t;
    }

    @Override
    public void run() {
        while (true){
            synchronized (ticket){
                if(ticket.getTicketNum() > 0){
                    ticket.sellOneTicket();
                    System.out.println("余票："+ticket.getTicketNum() + Thread.currentThread().getName());

                    try {
                        ticket.notifyAll();
                        ticket.wait();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName());
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("票卖完了" + Thread.currentThread().getName());
                    TicketManager.flag = true;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        Ticket tickets = new Ticket();

        ThreadFactory tf = new ThreadFactory() {
            private int i;
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("窗口" + i++);
                return thread;
            }
        };

        ExecutorService ex = Executors.newCachedThreadPool(tf);
        for(int i = 0; i < 10; i++){
            ex.execute(new TicketManager(tickets));
        }


        System.out.println(Thread.currentThread().getName() + TicketManager.flag);
        while (true){
            if(TicketManager.flag) {
                ex.shutdownNow();
                break;
            }
        }

        try {
            ex.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time = System.nanoTime() - start;

        System.out.printf("Tasks took %.3f ms to run%n", time/1e6);
    }
}
