package test.thread.pair;

public class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    public String toString(){
        return "x:" + x + " y:" + y;
    }

    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException(){
            super("pair avlues not rqual:" + Pair.this);
        }
    }

    public void checkState(){
        if(x != y){

            throw new PairValuesNotEqualException();
        }
    }

}
