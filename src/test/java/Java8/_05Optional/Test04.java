package Java8._05Optional;

import java.util.Optional;

class Teacher {
    int id;
    String name;

    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Teacher() {
    }
}

public class Test04 {


    public static Optional<String> getName(Teacher teacher){
        if(teacher.name==null){
            return Optional.empty();
        }
        return Optional.of(teacher.name);
    }


    public static void main(String[] args) {

        Optional<String> nameContainer = getName(new Teacher());
        Optional<String> nameContainer2 = getName(new Teacher(100, "Ramesh"));

        String res = nameContainer.or( ()->Optional.of("Optional Name")).get();
        System.out.println("nameContainer - Or: "+res);
        res = nameContainer2.or( ()->Optional.of("Optional Name")).get();
        System.out.println("nameContainer2 - Or: "+res);

        res = nameContainer.orElse("XYZ");  //get() not needed
        System.out.println("nameContainer - OrElse: "+res);
        res = nameContainer2.orElse("XYZ");
        System.out.println("nameContainer2 - OrElse: "+res);

        res = nameContainer.orElseGet(()-> "Optional Name");  //get() not needed
        System.out.println("nameContainer - orElseGet: "+res);
        res = nameContainer2.orElseGet(()-> "Optional Name");
        System.out.println("nameContainer2 - orElseGet: "+res);

//        res = nameContainer.orElseThrow();
//        System.out.println("nameContainer - orElseThrow: "+res);
        res = nameContainer2.orElseThrow();
        System.out.println("nameContainer2 - orElseThrow: "+res);
    }
}
