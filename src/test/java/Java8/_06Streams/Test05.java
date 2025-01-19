package Java8._06Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test05 {

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

        //17. Parallel Stream
        System.out.println("----------Sequential----------");
        employeeList().stream()
                .forEach(System.out::println);

        System.out.println("17. ----------paralel----------");
        employeeList().parallelStream()
                .forEach(System.out::println);

        //18. get all names of employees who's age is greater than 25
        List<String> emp25 = employeeList().stream()
                .filter(emp -> emp.getAge() > 25)
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("18. Employess Names age>25: "+emp25);

        //19. Unique Department names
        Set<String> department = employeeList().stream()
                .map(Employee::getDepartment).
                collect(Collectors.toSet());
        System.out.println("19. Unique departments: "+department);

        //20. Collect empIds and their salaries
        Map<Integer, Double> idSalary = employeeList().stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        System.out.println("20. toMap: "+idSalary);

        //20. toMap - BiOperator:  can be used when you want to handle conflicts in key values
        employeeList.add(new Employee(107,"Ram", "Hyd", 38, 'M', "Finance", 2025,45700));
        Map<Integer, Double> idSalaryBinary = employeeList.stream()
                .collect(Collectors
                        .toMap(Employee::getId,
                                Employee::getSalary,
                                (valSalary1, valSalary2) -> valSalary1 + valSalary2));
        System.out.println("20. toMap-BiOperator: "+idSalaryBinary);

        //20. toMap - Supplier - allows to choose the implementation of the Map (for example, HashMap, TreeMap, or LinkedHashMap).
        // TreeMap - to get sorted order
        TreeMap<String, Double> treeMap = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        (salary1, salary2) -> salary1, //keep salary of 1st available department
                        () -> new TreeMap<>()));
        System.out.println("20. toMap-Supplier-TreeMap: "+treeMap);

        //20. toMap - Supplier - allows to choose the implementation of the Map (for example, HashMap, TreeMap, or LinkedHashMap).
        // 20. LinkedHashMap - to maintain insertion order
        LinkedHashMap<String, Double> linkedHashMap = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        Double::sum, // add value1+value2
                        LinkedHashMap::new));
        System.out.println("20. toMap-Supplier-LinkedHashMap: "+linkedHashMap);

        //21. grouping()
        // average salary of each department
        Map<String, Double> avgSalaryDeptWise = employeeList().stream()
                .collect(Collectors.
                        groupingBy(Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("21. grouping: "+avgSalaryDeptWise);

        //21. Same as (20. toMap-BiOperator)
        Map<Integer, Double> idSalaryGrouping = employeeList.stream()
                .collect(Collectors
                        .groupingBy(Employee::getId,
                                Collectors.summingDouble(Employee::getSalary)
                        ));
        System.out.println("21. grouping: "+idSalaryGrouping);

    }
}