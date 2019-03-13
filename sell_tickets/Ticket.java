package test.thread.sell_tickets;

import java.util.concurrent.TimeUnit;

public class Ticket {
    private  int TicketNum = 100;

    public Ticket(){}
    public Ticket(int TicketNum){
        this.TicketNum = TicketNum;
    }

    public  void sellOneTicket() {
        TicketNum--;

    }

    public boolean checkStatus(){
        if(TicketNum <= 0){
            return false;
        }
        return true;
    }

    public int getTicketNum(){
        return  TicketNum;
    }
}
