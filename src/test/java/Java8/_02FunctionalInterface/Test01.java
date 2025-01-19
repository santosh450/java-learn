package Java8._02FunctionalInterface;

interface FI {
    void display();
}

class dummy implements FI {
    @Override
    public void display() {
        System.out.println("Display using implemented class");
    }
}

public class Test01 {
    public static void main(String[] args) {

        //traditional approche
        FI obj = new dummy();
        obj.display();

        // lambda expressions
        FI obj2 = () -> {
            System.out.println("obj2 Display using Lambda expressions");
        };
        obj2.display();

        FI obj3 = () -> System.out.println("obj3 Display using Lambda expressions");
        obj3.display();
    }
}
