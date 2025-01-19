//Reference to Instance Methods


package Java8._03MethodReference;


class Employee2 {
    int id, salary;
    String name;

    public Employee2(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee2() {
    }

    boolean isValidEmployee(Employee2 emp){
        return emp.id!=0 && emp.name!=null;
    }

    boolean validSalary(){
        return this.salary>0?true:false;
    }
}

@FunctionalInterface
interface Employee2FI {
    boolean isValidEmployeeFI(Employee2 emp);
}

public class Test03 {
    public static void main(String[] args) {

        Employee2 emp = new Employee2();
        //Access instance method with new Instance
        Employee2FI valid2 = emp::isValidEmployee;
        System.out.println("New Instance: "+valid2.isValidEmployeeFI(new Employee2()));
        System.out.println("New Instance: "+valid2.isValidEmployeeFI(new Employee2(10, "Ramesh", 1000)));

        //Access instance method with class Name
        Employee2FI validSal = Employee2::validSalary;
        System.out.println("ClassName: "+validSal.isValidEmployeeFI(new Employee2()));
        System.out.println("ClassName: "+validSal.isValidEmployeeFI(new Employee2(11, "Suresh", 5000)));
    }
}
