package Generics;

interface Printable{
    void print();
}

class MyNumber extends Number implements Printable
{
    private final int value;

    MyNumber(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println("MyNumber: "+value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value ;
    }


}

class Box06<T extends Number & Printable> {
    private T item;

    public Box06(T item){
        this.item=item;
    }

    public void display(){
        item.print();
    }

}

public class Generics07 {
    public static void main(String[] args) {
        MyNumber myNum = new MyNumber(12);
        Box06<MyNumber> box = new Box06<>(myNum);
        box.display();

    }
}
