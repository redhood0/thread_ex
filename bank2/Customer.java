package test.thread.bank2;

public class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }
    public int getServiceTime(){
        return serviceTime;
    }
    public String toString(){
        return "[" + serviceTime + "]";
    }

}
