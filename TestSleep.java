package test.thread;

import com.mysql.cj.jdbc.util.TimeUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class TestSleep implements Callable<Integer> {
    private static volatile int count;
    private final int id;
    private Random random;

    public TestSleep(Random radom) {
        id = count++;
        this.random = radom;
        System.out.println(id + "start");
    }

    @Override
    public Integer call() throws Exception {
        Integer t = random.nextInt(10);
        TimeUnit.SECONDS.sleep(t);
        System.out.println(id + "end-with--" + t + "second");
        return t;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            results.add(executorService.submit(new TestSleep(new Random())));
        }
        results.forEach(n -> {
                try {
                    System.out.println(n.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    executorService.shutdown();
                }

        });

    }
}
