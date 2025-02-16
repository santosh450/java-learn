package Generics;

class Box05<N extends Number>{

    private N length;
    public N getLength() {
        return length;
    }

    public void setLength(N length) {
        this.length = length;
    }

}
public class Generics05 {
    public static void main(String[] args) {
        //Valid
        Box05<Integer> box1 = new Box05<>();
        Box05<Float> box2 = new Box05<>();
        Box05<Byte> box3 = new Box05<>();
        Box05<Double> box4 = new Box05<>();

        //Box05<String> box5 = new Box05<>();
        //Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number'

    }
}
