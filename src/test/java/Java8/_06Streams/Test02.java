package Java8._06Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test02 {

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

        
        Stream<Employee> stream = employeeList().stream();

        //1. get all employees names
        //Stream Pipeline:
        //List<String> names = stream.map(emp -> emp.getName()).collect(Collectors.toList());
        List<String> names = stream.
                map(Employee::getName).
                collect(Collectors.toList());
        //Java 17: collect(Collectors.toList()) replaced with toList()
        System.out.println("1. Names: "+names);

        //2. Employee names age>30
        // IllegalStateException: stream has already been operated upon or closed
        /*
        List<String> names30 = stream.filter(emp -> emp.getAge() > 30).map(Employee::getName).collect(Collectors.toList());
        System.out.println("Age>30: "+names30);
         */
        List<String> names30 = employeeList().stream()
                .filter(emp -> emp.getAge() > 30)
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("2. Age>30: "+names30);

        //3. Print city names
        //Distinct/Unique
        employeeList().stream().map(Employee::getCity).distinct().forEach(System.out::print);
        //Duplicates
        System.out.println("\n3. ");
        employeeList().stream().map(Employee::getCity).forEach(e->System.out.print(e+" "));

        //4. No of employess salary>30K
        long count = employeeList().stream()
                .filter(e -> e.getSalary() > 30000)
                .count();
        System.out.println("\n4. Salary> 30K: "+count);

        //5. Get first 3 empoyees
        List<Employee> first3Emps = employeeList().stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("5. First 3 emps: "+first3Emps);

    }
}