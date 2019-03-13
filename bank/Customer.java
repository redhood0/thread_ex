package test.thread.bank;
//readonly class
public class Customer {
    private final int serviceTime;
    public Customer(int serviceTime){
        this.serviceTime = serviceTime;
    }
    public int getServiceTime(){
        return serviceTime;
    }
    public String toString(){
        return "[" + serviceTime + "]";
    }

}
