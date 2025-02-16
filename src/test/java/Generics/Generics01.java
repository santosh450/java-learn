package Generics;

class Box{

    private Object length;
    public Object getLength() {
        return length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

}
public class Generics01 {
    public static void main(String[] args) {
        Box box = new Box();
        box.setLength(10);
        String len = (String) box.getLength();
        System.out.println("Length: "+len );
    }
}
