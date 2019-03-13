package test.thread;

public class TicketSell {
    private  static  int ticketNum = 10;


    public void sell(){

        while(true) {
            if(ticketNum <= 0 ){
                System.out.println("停止售票");
                return;
            }
            synchronized (this){
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "售出票，为" + ticketNum + "号票");
                ticketNum--;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 4 ; i++)
        new Thread(() -> new TicketSell().sell()).start();
    }

}
