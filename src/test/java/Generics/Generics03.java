package Generics;

class Pair<K, V>{

    private K key;
    public V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
public class Generics03 {
    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(10, "Ten");
        System.out.println("Integer:String = "+pair1.getKey()+": "+pair1.getValue());

        Pair<String, Integer> pair2 = new Pair<>("Five", 5);
        System.out.println("String:Integer = "+pair2.getKey()+": "+pair2.getValue());
    }
}
