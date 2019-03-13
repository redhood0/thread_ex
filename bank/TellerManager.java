package test.thread.bank;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable{
    private ExecutorService executorService;
    private CustomerLine customers;
    //priority 优先级队列
    private PriorityQueue<Teller> workingTeller = new PriorityQueue<>();
    //普通队列，安置闲杂人员
    private Queue<Teller> tellerDoingOtherThings = new LinkedList<>();
    private int adjustmentPeriod;//调整 期间(时间)
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e,CustomerLine cl,int ap){
        executorService = e;
        customers = cl;
        adjustmentPeriod = ap;
        //start with a single teller
        Teller teller = new Teller(customers);
        executorService.execute(teller);
        workingTeller.add(teller);
    }

    public void adjustTellerNumber(){
        if(customers.size()/workingTeller.size() > 2){
            if(tellerDoingOtherThings.size() > 0){
                Teller teller = tellerDoingOtherThings.remove();
                teller.serveCustomerLine(); //没看懂的那句
                workingTeller.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            executorService.execute(teller);
            workingTeller.add(teller);//记得去看add源码
            return;
        }
        //if line is short enough ,remove a teller
        if(workingTeller.size() > 1 && customers.size() / workingTeller.size() <2){
            reassignOneTeller();
        }
        if(customers.size() == 0){
            while (workingTeller.size() > 1){
                reassignOneTeller();
            }
        }
    }

    private void reassignOneTeller() {
        Teller teller = workingTeller.poll();
        teller.doSomethingElse();
        tellerDoingOtherThings.add(teller);
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.println("customers " + "{");
                for(Teller teller : workingTeller){
                    System.out.println(teller + " ");
                }
                System.out.println("}");
            }

        }catch(InterruptedException e){
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    @Override
    public String toString(){
        return "TellerManager";
    }

}
