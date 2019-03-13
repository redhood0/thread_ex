package test.thread.bank;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * simulation 模拟
 */
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));

        exec.execute(new TellerManager(exec,customers,ADJUSTMENT_PERIOD));
//        System.out.println("press enter to quit");
//        System.in.read();
//        exec.shutdownNow();

    }
}
