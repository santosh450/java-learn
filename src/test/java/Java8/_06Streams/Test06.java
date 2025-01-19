package Java8._06Streams;

import java.util.*;
import java.util.stream.Collectors;

public class Test06 {

    public static List<Employee> employeeList(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(107,"Ram", "Hyd", 38, 'M', "Finance", 2019, 31700));
        list.add(new Employee(100,"Sai", "Che", 39, 'M', "Security", 2017, 38000));
        list.add(new Employee(45,"Radha", "Ban", 26, 'F', "Finance", 2000, 40000));
        list.add(new Employee(206,"Suresh", "Hyd", 32, 'M', "Sales", 2022, 22000));
        list.add(new Employee(18,"Abhi", "Mum", 45, 'M', "HR", 1996, 42000));
        return list;
    }

    public static void main(String[] args) {

        List<Employee> employeeList = employeeList();
        employeeList.add(new Employee(107,"Ram", "Hyd", 38, 'M', "Finance", 2025,45700));
        //count()
        //22. get employee count by gender wise
        Map<Character, Long> genderCount = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.counting()));
        System.out.println("22. count: "+genderCount);

        //summarizing
        System.out.println("23. Sum :"+
                employeeList.stream()
                .collect(Collectors.summingDouble(Employee::getSalary)));

        System.out.println("23. Summarizing :" +
                employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary)));

        DoubleSummaryStatistics stats = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("23 Stats: count: "+stats.getCount()+" min: "+stats.getMin());

        //maxBy or max
        Employee maxAge = employeeList.stream()
                .max((e1, e2) -> e1.getAge() - e2.getAge())
                .get();
        System.out.println("24. Max age Employee: "+maxAge);

        //minBy or min
        Employee minAge = employeeList.stream()
                .collect(Collectors.minBy((e1, e2) -> e1.getAge() - e2.getAge()))
                .get();
        System.out.println("24. Min age Employee: "+minAge);

        //joining()
        //All departments with delimiter:::
        String departments = employeeList.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.joining(":::"));
        System.out.println("25: joining: "+departments);
    }
}