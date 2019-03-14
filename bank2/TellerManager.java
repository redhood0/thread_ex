package test.thread.bank2;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable{
    private ExecutorService executorService;
    private CustomerLine customers;
    private PriorityQueue<Teller> serveTellers = new PriorityQueue<>();
    private Queue<Teller> otherTellers = new LinkedList<>();
    private int adjustmentPeriod;//调整 期间(时间)
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e, CustomerLine cl, int ap){
        executorService = e;
        customers = cl;
        adjustmentPeriod = ap;
        Teller teller = new Teller(customers);
        executorService.execute(teller);
        serveTellers.add(teller);
    }

    public void adjustTellerNumber(){
        if(customers.size()/serveTellers.size() > 2){
            if(otherTellers.size() > 0){
                Teller teller = otherTellers.remove();
                teller.backServe();
                serveTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            executorService.execute(teller);
            serveTellers.add(teller);
            return;
        }
        if(serveTellers.size() > 1 && customers.size() / serveTellers.size() <2){
            readjustTeller();
        }
        if(customers.size() == 0){
            while (serveTellers.size() > 1){
                readjustTeller();
            }
        }
    }

    private void readjustTeller() {
        Teller teller = serveTellers.poll();
        teller.doSomethingElse();
        otherTellers.add(teller);
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.println(customers + "{");
                for(Teller teller : serveTellers){
                    System.out.println(teller + " ");
                }
                System.out.println("}");
            }
        }catch(InterruptedException e){

        }
    }
}
