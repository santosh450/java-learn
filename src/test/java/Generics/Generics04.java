package Generics;

interface Container<T>{

    void add(T item);
    T get();
}

class Container1 implements Container<String> {

    @Override
    public void add(String item) {
    }
    @Override
    public String get() {
        return null;
    }
}

class Container2 implements Container<Integer> {

    @Override
    public void add(Integer item) {
    }
    @Override
    public Integer get() {
        return null;
    }
}

class Container3 implements Container<Integer> {

    private Integer item;
    @Override
    public void add(Integer item) {
        this.item=item;
    }
    @Override
    public Integer get() {
        return this.item;
    }
}

// Generic type should be defined for implemented Class<T> if we mention for Interface<T>
class GenericContainer<T> implements Container<T> {

    private T item;
    @Override
    public void add(T item) {
        this.item=item;
    }
    @Override
    public T get() {
        return item;
    }
}
public class Generics04 {
    public static void main(String[] args) {

    }
}
