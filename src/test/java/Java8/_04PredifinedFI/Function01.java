package Java8._04PredifinedFI;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Function01 {
    public static void main(String[] args) {

        Function<Integer, Integer> half = i->i/2;
        System.out.println(half.apply(10));

        Function<Integer, Integer> addBy3 = i->i+3;
        System.out.println(addBy3.apply(10));

        Function<String, String> upper = String::toUpperCase;
        System.out.println(upper.apply("Hello"));

        // default methods
        System.out.println("And Then: "+half.andThen(addBy3).apply(10));
        System.out.println("Compose: "+half.compose(addBy3).apply(10));

        //Static Method
        Function<Integer, Integer> identical = Function.identity();
        System.out.println("identity: "+identical.apply(10));

        //Error: input and Output should be same datatypes
        /*
        Function<Integer, double> identical2 = Function.identity();
        System.out.println("identity: "+identical.apply(10));
         */

        //Eg-2
        Stream<String> names = Stream.of("One", "Two", "three");
        List<String> vals = names.map(Function.identity()).collect(Collectors.toList());
        System.out.println("identity: "+vals);

    }
}
