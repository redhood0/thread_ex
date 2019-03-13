package test.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SimpleThreac extends Thread {
    private int countDpwm = 5;
    private static int threadCount = 0;
    public SimpleThreac(){
        super("" + ++threadCount);
        //start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            countDpwm--;
            if(countDpwm < 0){
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception {

//            FileInputStream in = new FileInputStream(new File("redhood.jpg"));
//
//            FileOutputStream out = new FileOutputStream("sbcky.jpg");
//
//        int lenth;
//        byte[] t = new byte[1024*8];
//        while((lenth = in.read(t)) != -1 ){
//            out.write(t);
//        }
        int a = 2;int b =2;
        ;
        System.out.println((a++)+(b++));
        //System.out.println((++a)*(++b));

    }
}
