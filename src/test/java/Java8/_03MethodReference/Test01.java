package Java8._03MethodReference;


interface ConvertUpper {
    String convert(String text);
}
public class Test01 {
    public static void main(String[] args) {

        //Lambda Expressions
        ConvertUpper upper = text -> text.toUpperCase();
        System.out.println(upper.convert("My Name is Santosh"));

        //Method Reference
        ConvertUpper upper2 = String::toUpperCase;
        System.out.println(upper2.convert("My Name is Maharana"));
    }
}
