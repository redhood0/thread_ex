package test.thread.pair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PairManager1 extends PairManager{
    private Lock lock = new ReentrantLock();
    @Override
    public  void increment() {
        Pair temp;
        lock.lock();
        try{
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }finally {
            lock.unlock();
        }
        store(temp);
    }
}

//use a critical section
class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incrementX();
            p.incrementY();
            temp = getPair();
            //store(temp);
        }
        store(temp);
    }
}
