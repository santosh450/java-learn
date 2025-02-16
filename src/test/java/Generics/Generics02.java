package Generics;

class Circe<T>{

    private T length;
    public T getLength() {
        return length;
    }

    public void setLength(T length) {
        this.length = length;
    }

}
public class Generics02 {
    public static void main(String[] args) {
        Circe<Integer> circle = new Circe<>();
        circle.setLength(10);
        Integer length = circle.getLength();
        System.out.println("Length1: "+length );

        Circe<String> circle2 = new Circe<>();
        circle2.setLength("Ten");
        String length2 = circle2.getLength();
        System.out.println("Length2: "+length2 );
    }
}
