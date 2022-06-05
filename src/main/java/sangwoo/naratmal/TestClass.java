package sangwoo.naratmal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int sum = multipleOne(getSum(integers));
        System.out.println("sum = " + sum);
    }

    private static int getSum(List<Integer> integers) {
        int sum = 0;
        for (Integer integer : integers) {
            sum += integer;
        }
        return sum;
    }
    private static int multipleOne(int number) {
        return number * 1;
    }
}
