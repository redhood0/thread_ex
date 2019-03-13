package test.thread.test.thread.date;

public abstract class IntGenerator {
    private volatile boolean  canceled = false;
    public abstract int next();
    //allow this to be canceled
    public void cancle(){
        canceled = true;
    }
    public boolean isCanceled(){
        return canceled;
    }

}
