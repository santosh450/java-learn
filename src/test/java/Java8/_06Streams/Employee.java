package Java8._06Streams;

public class Employee {
    private int id;
    private String name;
    private String city;
    private int age;
    private char gender;
    private String department;
    private int yearJoining;
    private double salary;

    public Employee(int id, String name, String city, int age, char gender, String department, int yearJoining, double salary) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearJoining = yearJoining;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearJoining() {
        return yearJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYearJoining(int yearJoining) {
        this.yearJoining = yearJoining;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', city='" + city + "', age=" + age + ", gender=" + gender + ", department='" + department + "', joiningYear=" + yearJoining + ", salary=" + salary + '}';
    }
}
