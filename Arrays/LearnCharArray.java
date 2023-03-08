package Arrays;

import java.util.*;

public class LearnCharArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String nameChar = scanner.nextLine();

        char name[] = new char[nameChar.length()];
        for (int i = 0; i < nameChar.length(); i++) {
            name[i] = nameChar.charAt(i);
        }

        int count = 0;

        System.out.println(Arrays.toString(name));
        System.out.println("Length of character array: " + count);
    }

}
