package Java8._01Interfaces;

 interface Interfaces041 {

    default void defaultMethodM1(){
        System.out.println("Inside default 041-M1");
    }

     default void defaultMethodM2(){
         System.out.println("Inside default 041-M2");
     }
}

interface Interfaces042 {

    default void defaultMethodM1(){
        System.out.println("Inside default 042-M1");
    }

    default void defaultMethodM3(){
        System.out.println("Inside default 042-M3");
    }
}

class TestInterface4 implements Interfaces041, Interfaces042{


    @Override
    public void defaultMethodM1() {
        Interfaces041.super.defaultMethodM1();
        // Or Interfaces042.super.defaultMethodM1();
        // Or Implement own logic
    }
}
public class Test04 {
    public static void main(String[] args) {
        TestInterface4 TI = new TestInterface4();
        TI.defaultMethodM1();
        TI.defaultMethodM2();
        TI.defaultMethodM3();
    }
}
