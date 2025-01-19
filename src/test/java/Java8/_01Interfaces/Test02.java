package Java8._01Interfaces;

 interface Interfaces02 {
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

class TestInterface2 implements Interfaces02{

    @Override
    public void printNames() {
        System.out.println("PrintNames in TestInterface");
        //Accessing default methods
        defaultMethod01();
    }

    @Override
    public String getMarks() {
        System.out.println("getMarks in TestInterface");
        //Accessing default methods
        defaultMethod02();
        return "getMarks";
    }
}
public class Test02 {
    public static void main(String[] args) {
        TestInterface2 TI = new TestInterface2();
        TI.printNames();
        TI.getMarks();
    }
}
