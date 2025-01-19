package Java8._06Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test03 {

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

        //6. Skip first 3
        List<Employee> skip3Emps = employeeList().stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("6. Skip 3 emps: "+skip3Emps);

        //7. verify any emp < 18
        boolean isUnder18 = employeeList().stream()
                .anyMatch(e -> e.getAge() < 18);
        //7. verify any emp < 30
        boolean isUnder30 = employeeList().stream()
                .anyMatch(e -> e.getAge() < 30);
        System.out.println("7. Any under 18 emp ?: "+isUnder18);
        System.out.println("7. Any under 30 emp ?: "+isUnder30);

        //8. Check every emp joined after 1995 or not
        boolean isAllJoinedAfter1995 = employeeList().stream()
                .allMatch(e -> e.getYearJoining() > 1995);
        //8. Check every emp joined after 2000 or not
        boolean isAllJoinedAfter2000 = employeeList().stream()
                .allMatch(e -> e.getYearJoining() > 2000);
        System.out.println("8. All are joined after 1995 ?: "+isAllJoinedAfter1995);
        System.out.println("8. All are joined after 2000 ?: "+isAllJoinedAfter2000);

        //9. None are joined before 1995
        boolean isNoneJoinedBefore1995 = employeeList().stream()
                .noneMatch(e -> e.getYearJoining() < 1995);
        //9. None are joined before 2000
        boolean isNoneJoinedBefore2000 = employeeList().stream()
                .allMatch(e -> e.getYearJoining() < 2000);
        System.out.println("9. None are joined before 1995 ?: "+isNoneJoinedBefore1995);
        System.out.println("9. None are joined before 2000 ?: "+isNoneJoinedBefore2000);

        //10. findAny() -> get one value from out of all values
        Optional<Employee> any = employeeList().stream()
                .findAny();
        Employee employee = any.get();
        System.out.println("10. Find Any: "+employee);

    }
}