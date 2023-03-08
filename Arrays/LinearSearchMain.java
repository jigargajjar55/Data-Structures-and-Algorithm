package Arrays;

import java.util.Scanner;

public class LinearSearchMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner scanner = new Scanner(System.in);

        int arrayNumbers[] = { 5, 7, -2, 10, 22, -2, 0, 5, 22, 1 };

        System.out.println("Enter the key: ");
        int key = scanner.nextInt();

        boolean found = search(arrayNumbers, key);

        if (found) {
            System.out.println("Key is found!");
        } else {
            System.out.println("Key is not found!");
        }

    }

    static boolean search(int aN[], int key) {

        int aNlength = aN.length;

        for (int i = 0; i < aNlength; i++) {
            if (aN[i] == key) {
                return true;
            }
        }
        return false;
    }

}
