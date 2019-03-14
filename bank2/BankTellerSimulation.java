package test.thread.bank2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankTellerSimulation {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(50);

        executorService.execute(new CustomerGenerator(customers));
        executorService.execute(new TellerManager(executorService,customers,1000));
    }
}
