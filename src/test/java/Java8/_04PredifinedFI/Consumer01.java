package Java8._04PredifinedFI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consumer01 {
    public static void main(String[] args) {

        Consumer<String> inUpper = x -> System.out.println(x.toUpperCase());
        inUpper.accept("Hello World");

        List<String> names = List.of("abc", "bcd", "efg");
        names.forEach(inUpper);

        List<String> newVals = new ArrayList<>();
        Consumer<String> addToList = newVals::add;
        names.forEach(addToList);
        System.out.println("New List: "+newVals);

        //default
        System.out.println("As Consumer not return anything...2 predicates work independently here");
        names.forEach(inUpper.andThen(addToList));
        System.out.println("New List2: "+newVals);
    }
}
