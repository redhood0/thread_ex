package test.thread.test.thread.date;

public class EvenGenerator extends IntGenerator {
    private volatile int currentEventValue = 0;
    @Override
    public synchronized int next() {
        ++currentEventValue;//danger point here
        Thread.yield();
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(),100);
    }
}
