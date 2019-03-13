package test.thread.pair;

public class PairManipulator implements Runnable {
    private PairManager pm;
    public PairManipulator(PairManager p){pm = p;}

    @Override
    public void run() {
        while(true){
            pm.increment();
        }
    }
    public String toString(){
        return "Pair: " + pm.getPair() + "checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable{
    private PairManager pm;
    public PairChecker(PairManager p){pm = p;}

    @Override
    public void run() {
        while(true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
