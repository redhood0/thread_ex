package test.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactorysb implements ThreadFactory{
    private  int priority = 5;

    public DaemonThreadFactorysb(){

    }

    public DaemonThreadFactorysb(int priority){ this.priority = priority;}


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setPriority(priority);
        return t;
    }

    public static void main(String[] args) {
        System.out.println("11");
        File[] files = File.listRoots();
        for(File file : files){
            System.out.println(file);
        }
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactorysb(6));
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
                File[] files = File.listRoots();
                for(File file : files){
                    System.out.println(file);
                }
            }
        });
    }

}
