package Java8._05Optional;

import java.util.Optional;

public class Test02 {

    public static Optional<String> getText(){
        String name = null;
        if(name==null){
            return Optional.empty();
        }
        return Optional.of("Ramesh");
    }


    public static void main(String[] args) {

        Optional<String> nameContainer = getText();
        if(nameContainer.isPresent()){
            System.out.println(nameContainer.get().toUpperCase());
        }
        else {
            System.out.println("Value is not available");
        }

        Optional<String> nameContainer2 = getText();
        if(nameContainer2.isEmpty()){
            System.out.println("Yes Empty");
        }
        else {
            System.out.println("Value is not available");
        }
    }


}
