package test.thread.test.thread.date;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttempLocking {
    private ReentrantLock reentrantLock = new ReentrantLock();
    public void untimed(){
        boolean captured = reentrantLock.tryLock();
        try{
            System.out.println("tryLock:" + captured);
        }finally {
            if(captured){
                reentrantLock.unlock();
            }
        }
    }

    public void timed(){
        boolean captured = false;
        try{
            captured = reentrantLock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("try lock:k(2, TimeUnit.SECONDS)" + captured);
        }finally {
            if(captured){
                reentrantLock.unlock();
            }
        }
    }

    public static  void main(String[] args) {
        File sb = new File("c:/Users/redhood/Documents/sb/cky/sb");
        sb.mkdirs();

        File[] files = sb.listFiles();
        String[] list = sb.list();
        System.out.println(list.length);
        for (String s : list){
            System.out.println(s + "-");
        }
        System.out.println(files);
        for(File file : files){
            System.out.println(file + "-");
        }

//        final AttempLocking attempLocking = new AttempLocking();
//        attempLocking.untimed();//True -- lock is available
//        attempLocking.timed();//True -- lock is available
//        new Thread(){
//            {
//                setDaemon(true);
//            }
//            @Override
//            public void run() {
//                attempLocking.reentrantLock.lock();
//                System.out.println("acquired");
//            }
//        }.start();
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread.yield();
//
//        attempLocking.timed();
//        attempLocking.untimed();
    }
}
