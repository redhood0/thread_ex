package test.thread.pair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
    static void testApproaches(PairManager pairManager1,PairManager pairManager2){
        ExecutorService executorService = Executors.newCachedThreadPool();
        PairManipulator pm1 = new PairManipulator(pairManager1),
                pm2 = new PairManipulator(pairManager2);
        PairChecker pc1 = new PairChecker(pairManager1),
                pc2 = new PairChecker(pairManager2);
        executorService.execute(pm1);
        executorService.execute(pm2);
        executorService.execute(pc1);
        executorService.execute(pc2);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
            e.printStackTrace();
        }
        System.out.println("pm1: " + pm1 + "\npm2 :" + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager p1 = new PairManager1(),
                p2 = new PairManager2();
        testApproaches(p1,p2);
    }
}
