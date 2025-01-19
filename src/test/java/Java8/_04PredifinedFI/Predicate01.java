package Java8._04PredifinedFI;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Employee {
    String name;
    int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object obj){
        Employee e = (Employee) obj;
        return this.getSalary()==e.getSalary();
    }
}
public class Predicate01 {
    public static void main(String[] args) {

        Predicate<Integer> legalAge = age -> age>21;
        System.out.println("Legal Age: "+legalAge.test(25));

        Predicate<Employee> salaryCheck = emp -> emp.getSalary()>25000;
        Predicate<Employee> nameCheck = emp -> emp.getName().startsWith("S");

        Employee emp1 = new Employee("Shiva", 26000);
        System.out.println("Salary: "+salaryCheck.test(emp1));
        System.out.println("Name: "+nameCheck.test(emp1));
        Employee emp2 = new Employee("Ram", 24000);
        System.out.println("Salary: "+salaryCheck.test(emp2));
        System.out.println("Name: "+nameCheck.test(emp2));

        //Default
        System.out.println("And: "+salaryCheck.and(nameCheck).test(emp1));
        System.out.println("Or: "+salaryCheck.or(nameCheck).test(emp2));
        System.out.println("Negate: "+salaryCheck.or(nameCheck).negate().test(emp2));

        //Static
        Predicate<Employee> baseEmp = Predicate.isEqual(emp1);
        Employee emp3 = new Employee("Rahim", 26000);
        System.out.println("Is Equal: "+baseEmp.test(emp3));

    }
}
