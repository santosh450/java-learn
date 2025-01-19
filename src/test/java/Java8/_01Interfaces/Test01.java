package Java8._01Interfaces;

 interface Interfaces01 {
    void printNames();
    String getMarks();

    default void defaultMethod01(){
        System.out.println("Inside default 01");
    }

     default String defaultMethod02(){
         System.out.println("Inside default 02");
         return "defaultMethod02";
     }
}

class TestInterface implements Interfaces01{

    @Override
    public void printNames() {
        System.out.println("PrintNames in TestInterface");
    }

    @Override
    public String getMarks() {
        System.out.println("getMarks in TestInterface");
        return "getMarks";
    }
}
public class Test01 {
    public static void main(String[] args) {
        TestInterface TI = new TestInterface();
        TI.printNames();
        TI.getMarks();
        TI.defaultMethod01();
        TI.defaultMethod02();
    }
}
