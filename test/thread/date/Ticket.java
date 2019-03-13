package test.thread.test.thread.date;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public  class  Ticket {
    private int num = 10;
    //private volatile boolean isSelled = true;
    private Lock lock = new ReentrantLock();
    public Ticket(int i){
        num = i;
    }
    public  int minusT(){
        lock.lock();
        try {
            num--;
            return num;
        }finally {
            lock.unlock();
        }
    }
    public synchronized int getT(){
        return num;
    }
}
