package Java8._04PredifinedFI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Supplier01 {
    public static void main(String[] args) {

        Supplier<LocalDateTime> time = LocalDateTime::now;
        System.out.println(time.get());

        //passing FI as method argument
        printTime(time);
    }

    public static void printTime(Supplier<LocalDateTime> now){
        System.out.println("passing FI as method argument: \n"+now.get());
    }
}
