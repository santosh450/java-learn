package Java8._02FunctionalInterface;

interface OperationsFI {
    double display(int x, int y);
}


public class Test02 {
    public static void main(String[] args) {

        // lambda expressions
        OperationsFI add = (x,y) -> x+y;
        OperationsFI minus = (x,y) -> x-y;
        OperationsFI mul = (x,y) -> x*y;
        OperationsFI div = (x,y) -> x/y;

        System.out.println(add.display(10, 2));
        System.out.println(minus.display(10, 2));
        System.out.println(mul.display(10, 2));
        System.out.println(div.display(10, 2));

    }
}
