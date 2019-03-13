package test.thread;

import java.util.ArrayList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            results.add(service.submit(new TaskWithResult(i)));
        }
//        for(Future<String> future : results) {
//
//            try {
//                System.out.println(future.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }finally {
//                service.shutdown();
//            }
//        }
        results.forEach((n) -> System.out.println(n.isDone()));
    }
}
