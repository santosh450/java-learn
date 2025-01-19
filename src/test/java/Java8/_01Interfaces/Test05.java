package Java8._01Interfaces;

 interface Interfaces051 {

    static void staticMethodM1(){
        System.out.println("Inside static 051-M1");
    }

     static void staticMethodM2(){
         System.out.println("Inside static 051-M2");
     }
}

interface Interfaces052 {

    static void staticMethodM1(){
        System.out.println("Inside static 052-M1");
    }

    static   void staticMethodM3(){
        System.out.println("Inside static 052-M3");
    }
}

class TestInterface5 implements Interfaces051, Interfaces052{
    void check(){
        Interfaces051.staticMethodM1();
        Interfaces051.staticMethodM2();
        Interfaces052.staticMethodM1();
        Interfaces052.staticMethodM3();
        System.out.println();
    }

}
public class Test05 {
    public static void main(String[] args) {
        TestInterface5 TI = new TestInterface5();
        TI.check();
        Interfaces051.staticMethodM1();
        Interfaces051.staticMethodM2();
        Interfaces052.staticMethodM1();
        Interfaces052.staticMethodM3();
    }
}
