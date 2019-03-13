package test.thread;


import sun.nio.cs.Surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Fibonacci implements Callable<String> {
    private int count=0;
    private final int id;
    public Fibonacci(int n) { id = n; }

    public Integer next(){
        return fib(count++);
    }

    private int fib(int n){
       if(n < 2){
           return 1;
       }
       return fib(n-2) + fib(n-1);
    }

    @Override
    public String call() throws Exception {
        Integer[] sequence = new Integer[id];
        for(int i = 0; i < id; i++)       sequence[i] = next();
        return "序列为：" + Arrays.toString(sequence) + "---第" + id + "组";
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            results.add(service.submit(new Fibonacci(i)));
        }
        results.forEach(n -> {
            try {
                System.out.println(n.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                service.shutdown();
            }
        });
    }
}
