package Generics;

interface Printable01{
    void print();
}

class MyNumber01 implements Printable01
{
    private final int value;

    MyNumber01(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println("MyNumber: "+value);
    }


    public void printOwn() {
        System.out.println("MyNumber Own : "+value);
    }



}

// Boundary with Interface and its methods
class Box07<T extends Printable01> {
    private T item;

    public Box07(T item){
        this.item=item;
    }

    public void display01(){
        item.print();
    }

    public void display02(){
        //item.printOwn();
        //You Cant access PrintOwn method
    }

}

// Boundary with Class and its own methods, implemented methods
class Box08<T extends MyNumber01> {
    private T item;

    public Box08(T item){
        this.item=item;
    }

    public void display03(){
        item.print();
    }

    public void display04(){
        item.printOwn();
        //You Can access both Interface and class  methods
    }

}

public class Generics06 {
    public static void main(String[] args) {
        MyNumber01 myNum = new MyNumber01(13);
        Box07<MyNumber01> box = new Box07<>(myNum);
        box.display01();

        MyNumber01 myNum2 = new MyNumber01(14);
        Box08<MyNumber01> box2 = new Box08<>(myNum2);
        box2.display03();
        box2.display04();

    }
}
