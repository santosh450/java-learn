package Java8._06Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Test01 {


    public static void main(String[] args) {

        //01. From Collection objects
        List<String> list1 = List.of("One", "Two");
        Stream<String> stream = list1.stream();

        //02. Array of values
        String[] str1 = {"One", "Two"};
        Stream<String> stream2 = Arrays.stream(str1);

        //03. Stream Methods
        Stream<String> Stream3 = Stream.of("One", "Two");

        //04. generate()
        Stream<String> Stream4 = Stream.generate(() -> "One");

        //05. builder()
        //Builder design pattern
        Stream.Builder<Object> builder = Stream.builder();
        Stream<Object> build = builder.add("One").add("Two").add("Three").build();

        //06. Empty Stream
        // empty()

        Stream<Object> empty = Stream.empty();


    }
}
