package SOLID.ISP;

public class BackendDeveloper implements Coder, Tester{
    @Override
    public void writeCode() {
        System.out.println("Backend engineer can write code");
    }

    @Override
    public void testCode() {
        System.out.println("Backend engineer can test code");
    }

}
