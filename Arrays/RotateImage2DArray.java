package Arrays;

import java.util.Arrays;

public class RotateImage2DArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        // int matrix[][] = {{},{},{},{}};

        for (int arr[] : matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-------------------------------");
        rotateAntiClockWise(matrix);

        for (int arr[] : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void rotateClockWise(int[][] matrix) {

        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        reverseRows(matrix);

        for (int i = 0; i < rowSize; i++) {
            for (int j = i + 1; j < colSize; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // Reverse row of the matrix
    public static void reverseRows(int[][] matrix) {

        int start = 0;
        int end = matrix.length - 1;

        while (start < end) {

            int temp[] = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = temp;

            start++;
            end--;

        }
    }

    public static void rotateAntiClockWise(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            reverseCols(matrix[i], matrix[i].length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    public static void reverseCols(int[] arr, int size) {

        int start = 0;
        int end = size - 1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

}
