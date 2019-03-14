package test.thread.bank2;

import java.util.concurrent.TimeUnit;

public class Teller implements Runnable, Comparable<Teller> {
    private static int count = 0;
    private final int id = count++;
    private int serveCustomerNo = 0;
    private CustomerLine customers;
    private boolean serveStatus = true;

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    public synchronized void doSomethingElse() {
        serveCustomerNo = 0;
        serveStatus = false;//无服务状态
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void backServe() {
        if (serveStatus == true) {
            System.out.println("already serve");
        }
        serveStatus = true;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    serveCustomerNo++;
                    while (!serveStatus) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("teller interrupted");
        }
    }

    @Override
    public String toString() {
        return "Teller" + id + "--" + serveCustomerNo;
    }

    @Override
    public int compareTo(Teller o) {
        return this.serveCustomerNo < o.serveCustomerNo ? -1 : (this.serveCustomerNo == o.serveCustomerNo ? 0 : 1);
    }
}
