package Generics;

import java.util.Arrays;
import java.util.List;

//Not supported
//class MyException<T> extends Exception {

//Alternate way to achieve this
class MyException extends Exception {
    public <T> MyException(T value){
        super("Exception value: "+value.toString()
                +" of type: "+value.getClass().getName());
    }
}

public class Generics12_Exception {

    public static void main(String[] args) {
        try{
            throw new MyException(100);
        }catch (MyException e){
            System.out.println("Caught Exception: "+e.getMessage());
        }
        try{
            throw new MyException("Two");
        }catch (MyException e){
            System.out.println("Caught Exception: "+e.getMessage());
        }
    }

}



