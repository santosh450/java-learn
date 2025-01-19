package Java8._05Optional;

import java.util.Optional;

public class Test01 {
    public static String getName(){
        return null;
    }

    public static Optional<String> getText(){
        return Optional.of("Ramesh");
    }


    public static void main(String[] args) {
        String name = getName();  //No gaurantee that value is always not null

        if (name!=null)           // should always null check in each cases
            name.toUpperCase();

        System.out.println(name);

        //Optional approach

        Optional<String> nameContainer = getText();
        if(nameContainer.isPresent()){
            System.out.println(nameContainer.get().toUpperCase());
        }
    }


}
