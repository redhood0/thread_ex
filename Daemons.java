package test.thread;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

public class Daemons {

    public static void main(String[] args) throws InterruptedException {
       Thread d = new Thread(new Daemon());
       d.setDaemon(true);
       d.start();
       println("d is daemon? :" + d.isDaemon());
        TimeUnit.SECONDS.sleep(3);
    }

}

class Daemon implements Runnable{
    private Thread[] t = new Thread[10];
    @Override
    public void run() {
        for(int i = 0; i < t.length; i++){
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            println("spawn start" + t[i].getName());
        }
        System.out.println("---------------");
        List i = Arrays.asList(t);
        ArrayList<Thread> arrayList = new ArrayList<>(i);
        arrayList.forEach(n -> System.out.println(n.getName()+" is deamon :" + n.isDaemon()));
        while(true) Thread.yield();
    }
}

class DaemonSpawn implements Runnable{
    private Thread[] t = new Thread[10];
    @Override
    public void run() {
        while(true) Thread.yield();
    }
}
