package Generics;

class Box09 {

    //private T value;
    //Cannot resolve symbol 'T'
    public <T> Box09(T value){
        System.out.println("Value in Box09: "+value);
    }
}

class Box10 {

    public <T extends Number> Box10(T value){
        System.out.println("Value in Box10: "+value);
    }
}
public class Generics08_Constructor {

    public static void main(String[] args) {

        Box09 box9 = new Box09(10);
        box9 = new Box09("Ten");
        Box10 box10 = new Box10(11);
        box10 = new Box10(11.00);

    }
}
