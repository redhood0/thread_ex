package test.thread.test.thread.date;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();

            if (val % 2 != 0) {
                System.out.println(val + "not even!" + Thread.currentThread().getName());
                intGenerator.cancle();
            }
        }
    }

    //Test any type of IntGenerator:
    public static void test(IntGenerator intGenerator, int count) {
        System.out.println("Press Control-Alt-C to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(intGenerator, i));
        }
        executorService.shutdown();
    }
}
