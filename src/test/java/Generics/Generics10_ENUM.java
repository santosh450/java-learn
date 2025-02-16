package Generics;


enum Operation {
    ADD, MULTIPLY, SUBTRACT, DIVIDE;

    public <T extends Number> double apply (T a, T b){
        switch (this){
            case ADD:
                return a.doubleValue()+b.doubleValue();
            case SUBTRACT:
                return a.doubleValue()-b.doubleValue();
            case DIVIDE:
                return a.doubleValue()/b.doubleValue();
            case MULTIPLY:
                return a.doubleValue()*b.doubleValue();
            default:
                throw new AssertionError("Unknown operation "+this);
        }
    }
}
public class Generics10_ENUM {

    public static void main(String[] args) {
        double add = Operation.ADD.apply(10, 20);
        System.out.println("Add: "+add);
        double mul = Operation.MULTIPLY.apply(10, 20);
        System.out.println("Mul: "+mul);
    }
}
