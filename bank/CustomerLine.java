package test.thread.bank;

import java.util.concurrent.ArrayBlockingQueue;
//顾客容器
public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }
    public String toString(){
        if(this.size() == 0){
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for(Customer c : this){
            result.append(c);
        }
        return result.toString();
    }
}
