//Reference to Static Methods


package Java8._03MethodReference;


class Employee {
    int id;
    String name, address;

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Employee() {
    }

    static boolean isValidEmployee(Employee emp){
        return emp.id!=0 && emp.name!=null;
    }
}

@FunctionalInterface
interface EmployeeFI {
    boolean isValidEmployeeFI(Employee emp);
}

public class Test02 {
    public static void main(String[] args) {

        //Method Reference
        EmployeeFI valid = Employee::isValidEmployee;
        System.out.println(valid.isValidEmployeeFI(new Employee()));
        System.out.println(valid.isValidEmployeeFI(new Employee(10, "Ramesh", "Hyderabad")));
    }
}
