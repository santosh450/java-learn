package Java8._05Optional;

import java.util.Optional;

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }
}

public class Test03 {


    public static Optional<String> getName(Teacher teacher){
        if(teacher.name==null){
            return Optional.empty();
        }
        return Optional.of(teacher.name);
    }


    public static void main(String[] args) {

        Optional<String> nameContainer = getName(new Teacher());
        if(nameContainer.isPresent()){
            System.out.println(nameContainer.get());
        }
        else {
            System.out.println("Value is not available");
        }


        Optional<String> nameContainer2 = getName(new Teacher(100, "Ramesh"));
        if(nameContainer2.isEmpty()){
            System.out.println("Yes Empty");
        }
        else {
            System.out.println("nameContainer2: "+nameContainer2.get());
        }
        nameContainer2.ifPresent(x-> System.out.println("nameContainer2 - ifPresent: "+x.toUpperCase()));
        nameContainer2.ifPresentOrElse(
                x-> System.out.println("nameContainer2 - ifPresentOrElse: "+x.toLowerCase()),
                () -> System.out.println("nameContainer2 - ifPresentOrElse: ELSE PART")
        );

        Optional<String> nameContainer3 = getName(new Teacher());
        nameContainer3.ifPresent(x-> System.out.println("nameContainer3 - ifPresent: "+x.toUpperCase()));
        nameContainer3.ifPresentOrElse(
                x-> System.out.println("nameContainer3 - ifPresentOrElse: "+x.toLowerCase()),
                () -> System.out.println("nameContainer3 - ifPresentOrElse: ELSE PART")
        );
    }


}
