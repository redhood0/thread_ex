package test.thread;


import java.io.IOException;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

public class Joining {
    public static void main(String[] args) throws IOException {
        Sleeper
                sleepey = new Sleeper("Slepy", 3300),
                grumpy = new Sleeper("grumpy" ,3300);
        Joiner
                dopey = new Joiner("Dopey",sleepey),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();

    }
}

class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name, int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }
    @Override
    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            println(getName() + "was interrupted" +
                    "isInterrupted():" + isInterrupted());
            return;
        }
        println(getName() + "was awakened");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run(){
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            println( " interrupted");
        }
        println(getName() + "join completed");
    }
}

