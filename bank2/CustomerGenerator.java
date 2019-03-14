package test.thread.bank2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine cl) {
        customers = cl;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(400));
                Customer c = new Customer(rand.nextInt(1400));
                customers.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator Interrupted");
        }
        System.out.println("CustomerGenerator Terminating");
    }
}
