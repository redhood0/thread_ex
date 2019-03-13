package test.thread.test.thread.date;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Audience implements Runnable{
    private Ticket ticket;
    public Audience(Ticket ticket1){
        ticket = ticket1;
    }

    @Override

    public void run() {
        int t = ticket.minusT();
        System.out.println(t);
        if(t <= 0){
            System.out.println("没票了");
            return;
        }

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Ticket ticket = new Ticket(10);
        for(int i = 0;i < 10; i++){
            executorService.execute(new Audience(ticket));
        }
    }

}
