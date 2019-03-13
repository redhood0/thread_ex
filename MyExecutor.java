package test.thread;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.analysis.Executor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service1 = Executors.newFixedThreadPool(10);
        ExecutorService service2 = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 5; i++){
           // service.execute(new LiftOff());
            //service1.execute(new LiftOff());
            service2.execute(new LiftOff());
        }
        List<Runnable> runnableList =  service2.shutdownNow();

        System.out.println(runnableList);
        //service1.shutdown();

    }
}
