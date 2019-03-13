package test.thread;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {
    private Integer id;
    public TaskWithResult(Integer id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "no." + id;
    }
}
