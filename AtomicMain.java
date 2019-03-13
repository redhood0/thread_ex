package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicMain {
    private static int x = 0   ;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            synchronized (AtomicMain.class){
            service.execute(() -> x++);}
        }
        service.shutdown();


        System.out.println(x);

    }

}