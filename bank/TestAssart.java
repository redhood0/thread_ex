package test.thread.bank;

public class TestAssart {
    public static void main(String[] args) {
        boolean flag=1>2;
        assert flag:"flag is false";

        System.out.println("断言通过");
    }
}
