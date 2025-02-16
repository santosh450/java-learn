package Generics;
public class Generics09_Methods {

    public <T> void printArray(T[] array){
        for (T ele: array) {
            System.out.print(" Element: "+ele);
        }
        System.out.println();
    }

    public <T> void display (T element){
        System.out.println("Generic display: "+element);
    }
    public void display (Integer element){
        System.out.println("Integer display: "+element);
    }

    public static void main(String[] args) {

        Integer[] intArray = {1,2,3};
        String[] strArray = {"Hello", "Tata"};
        Generics09_Methods g09 = new Generics09_Methods();
        g09.printArray(strArray);
        g09.printArray(intArray);

        g09.display(10);
        g09.display(10.0);


    }
}
