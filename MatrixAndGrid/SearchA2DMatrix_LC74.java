package MatrixAndGrid;
import java.util.*;

public class SearchA2DMatrix_LC74 {
    

    public boolean searchMatrix(int[][] matrix, int target) {

        //Time: O(log(M * N)), Space: O(1)
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        int start = 0;
        int end = ((rowSize * colSize) - 1);

        while(start <= end){
            int mid = start + ((end - start)/2);

            int element = matrix[mid/colSize][mid % colSize];

            if(element == target){
                return true;
            }else if(element < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return false;      
        
    }
}
