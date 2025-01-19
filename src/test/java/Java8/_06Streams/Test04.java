package Java8._06Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test04 {

    public static List<Employee> employeeList(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(107,"Ram", "Hyd", 38, 'M', "Finance", 2019, 31700));
        list.add(new Employee(100,"Sai", "Che", 39, 'M', "Security", 2017, 38000));
        list.add(new Employee(45,"Rakesh", "Ban", 26, 'F', "Finance", 2000, 40000));
        list.add(new Employee(206,"Suresh", "Hyd", 32, 'M', "Sales", 2022, 22000));
        list.add(new Employee(18,"Abhi", "Mum", 45, 'M', "HR", 1996, 42000));
        return list;
    }

    public static void main(String[] args) {

        //10. findAny() -> get one value from out of all values
        Optional<Employee> any = employeeList().stream()
                .findAny();
        Employee employee = any.get();
        System.out.println("10. Find Any: "+employee);

        //11. findFirst() -> return first value
        Optional<Employee> first = employeeList().stream()
                .findFirst();
        Employee firstEmployee = any.get();
        System.out.println("11. Find first: "+firstEmployee);

        //12. sorted
        List<Integer> collect = employeeList().stream()
                .map(Employee::getId)
                .sorted().
                collect(Collectors.toList());
        System.out.println("12. Sort EmpId: "+collect);
        //12. sorted - Comparator
        List<Employee> collect1 = employeeList().stream()
                .sorted((e1, e2) -> e1.getId() - e2.getId()).
                collect(Collectors.toList());
        System.out.println("12. Sort EmpId with comparator: "+collect1);
        //12. sorted - Comparator
        employeeList().stream()
                .sorted((e1, e2) -> e1.getId() - e2.getId()).
                forEach(e->System.out.print("12. Sort: "+e.getId()+"-"+e.getSalary()+" "));

        //13.Minimum Salary
        Employee minSalary = employeeList().stream()
                .min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println("\n13. Minimum Salary: "+minSalary);

        //14.Maximum Salary
        Employee maxSalary = employeeList().stream()
                .max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println("14. Maximum Salary: "+maxSalary);

        //15. Average Salary
        OptionalDouble average = employeeList().stream()
                .mapToDouble(Employee::getSalary)       //MapToDouble is required as getSalary is double; average() should work on Double
                .average();
        double v = average.isPresent() ? average.getAsDouble() : 0.00;
        System.out.println("15. Average Salary: "+v);

        //15. Average Age
        double asDouble = employeeList().stream()
                .mapToInt(Employee::getAge)       //mapToInt is required as getAge is int; average() should work on Double
                .average().getAsDouble();
        System.out.println("15. Average Age: "+asDouble);


        //peek()
        System.out.println("----peak()---------");
        List<Employee> empAfter2015 = employeeList().stream()
                .peek(emp-> System.out.println("Before filter: "+emp))
                .filter(emp -> emp.getYearJoining() > 2015)
                .peek(emp-> System.out.println("After filter: "+emp))
                .collect(Collectors.toList());
        System.out.println("16. peak result: "+empAfter2015);

        //peek()
        List<String> peekEg = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("16. peek Eg: "+peekEg);
    }
}