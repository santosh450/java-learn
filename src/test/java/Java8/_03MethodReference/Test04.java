// 3. Reference to Instance Methods of arbitrary object of given type


package Java8._03MethodReference;


import java.util.Arrays;

public class Test04 {
    public static void main(String[] args) {

        //Traditional
        String[] names = {"Ravi", "Bipin", "Ranga", "Durga"};
        Arrays.sort(names, (o1, o2)->o1.compareToIgnoreCase(o2));
        System.out.println(Arrays.toString(names));

        //Method reference
        String[] city = {"Hyderabad", "Delhi", "Bangalore", "Chennai"};
        Arrays.sort(city, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(city));

    }
}
