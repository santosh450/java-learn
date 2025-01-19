//Reference to Instance Methods


package Java8._03MethodReference;


class Employee4 {
    int id, salary;
    String name;

    public Employee4() {
        System.out.println("Default Constructor");
    }

    Employee4(int id, String name, int salary) {
        System.out.println("Implemented Constructor");
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

}

@FunctionalInterface
interface Employee4FI {
    Employee4 setEmployeeData(int id, String name, int salary);
}

public class Test05 {
    public static void main(String[] args) {

        Employee4FI empFI4 = Employee4::new;
        Employee4 emp4 = empFI4.setEmployeeData(10, "Ramu", 10000);
        System.out.println("Constructor Reference: "+emp4.id);

        Employee4 emp5 = empFI4.setEmployeeData(11, "Rakesh", 20000);
        System.out.println("Constructor Reference: "+emp5.id);
    }
}
