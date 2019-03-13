package test.thread;

public class Ticket {
    private int num = 10;
    public int  get(){
        return num;
    }
    public void buyOneTicket(){
        num--;
    }
}