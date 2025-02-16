package Generics;

import java.util.Arrays;
import java.util.List;

public class Generics11_Wild {

    public static void main(String[] args) {
        List<? super Number> nums = Arrays.asList(1, 2, 3);
        nums.add(10);

        List<? extends Number> num2 = Arrays.asList(1, 2, 3);
        //num2.add(10);
        //Error
    }

    public double sum1(List<? extends Number> nums){
        double sum = 0;
        for (Number n:nums) {
            sum +=n.doubleValue();
        }
        return sum;
    }
}
