package test.thread.bank;

import java.util.concurrent.TimeUnit;


//出纳员，计票员
public class Teller implements Runnable, Comparable<Teller> {
    private static int count = 0;
    private final int id = count++;
    //Customers served during this shift(转变)
    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cl) {
        customers = cl;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customerServed++;
                    //记录服务人数，为什么要加锁？我思考一下，可能是同一个计票员跑去了不同线程，如果同时处理多个客户可能会造成人数计算不准确的问题
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doSomethingElse() {
        customerServed = 0;
        servingCustomerLine = false;
    }

    /**
     * 这句没看懂，TODO：
     */
    public synchronized void serveCustomerLine() {
        //断言表达式，如果为true 继续运行。
        assert !servingCustomerLine : "already serving:" + this;
        servingCustomerLine = false;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller" + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }
    //Used by priority queue:
    @Override
    public synchronized int compareTo(Teller other) {
        return customerServed < other.customerServed ? -1 : (customerServed == other.customerServed ? 0 : 1);
    }

}
