package Java8._01Interfaces;

 interface Interfaces03 {
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

class TestInterface3 implements Interfaces03{

    @Override
    public void printNames() {
        System.out.println("PrintNames in TestInterface");
    }

    @Override
    public String getMarks() {
        System.out.println("getMarks in TestInterface");
        return "getMarks";
    }

    @Override
    public void defaultMethod01() {
        System.out.println("Override defaultMethod01 in TestInterface3");
    }
}
public class Test03 {
    public static void main(String[] args) {
        TestInterface3 TI = new TestInterface3();
        TI.defaultMethod01();
        TI.defaultMethod02();
    }
}
