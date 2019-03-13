package test.thread;

import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

public class SimpleDaemon implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("daemon thread" + Thread.currentThread());
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SimpleDaemon(),"daemon thread");
        thread.setDaemon(true);
        println("all daemon start");
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
