
import java.util.Arrays;

public class ReverseAnArray {

    public static void main(String[] args) {

        int arrayNumbers[] = { 5, 7, -2, 10, 22, -2, 5, 22, 1 };

        System.out.println(Arrays.toString(arrayNumbers));

        reverseArray(arrayNumbers);

        System.out.println(Arrays.toString(arrayNumbers));

    }

    static void reverseArray(int[] aN) {

        int aNLength = aN.length;

        int start = 0;
        int end = aNLength - 1;

        while (start <= end) {

            int temp = aN[start];
            aN[start] = aN[end];
            aN[end] = temp;

            start += 1;
            end -= 1;
        }
    }

}
