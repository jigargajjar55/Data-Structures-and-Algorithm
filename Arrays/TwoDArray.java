package Arrays;

import java.util.*;

public class TwoDArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub\
        Scanner scanner = new Scanner(System.in);
        int arr[][] = new int[3][4];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                arr[row][col] = scanner.nextInt();
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }

        // Linear Search in 2D Array
        // System.out.println("Please enter target: ");
        // int target = scanner.nextInt();
        // if(isFound(arr,target,3,4)) {
        // System.out.println("Element is found!");
        // }else {
        // System.out.println("Element is not found!");
        // }

    }

    static boolean isFound(int nums[][], int target, int row, int col) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (nums[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

}
