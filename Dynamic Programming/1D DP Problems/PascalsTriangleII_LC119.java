import java.util.*;

public class PascalsTriangleII_LC119 {


    // https://leetcode.com/problems/pascals-triangle-ii/solutions/1949770/simple-solution-with-explanation-of-combination-for-beginners-beats-100-o-n/

    //Time: O(N),
    //Space: O(N){N = RowIndex}
    public List<Integer> getRow(int rowIndex) {

        List<Integer> result = new ArrayList<>();
        long ncr = 1;

        for(int i=0; i <= rowIndex; i++){
            result.add((int)ncr);

            long ncr1 = (ncr * (rowIndex - i) / (i + 1));

            ncr = ncr1;            
        }

        return result;
        
    }




    //Time: O(N ^ 2)
    //Space: O(N){N = RowIndex}
    public List<Integer> getRow2(int rowIndex) {

        List<Integer> result = new ArrayList<>();

        for(int i=0; i <= rowIndex; i++){
            result.add(1);

            for(int j=i-1; j>0; j--){

                int element = result.get(j) + result.get(j-1);

                result.set(j, element);
            }
        }

        return result;
        
    }

    //Bruteforce Approach
    //Time: O(N ^ 2),
    //Space: O(N ^ 2){N = RowIndex}
    public List<Integer> getRow1(int rowIndex) {

        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<= rowIndex; i++){

            result.add(new ArrayList<>());

            for(int j=0; j<=i; j++){
                if(j == 0 || j == i){
                    result.get(i).add(1);
                }else{

                    int ans = result.get(i-1).get(j-1) + result.get(i-1).get(j);

                    result.get(i).add(ans);
                }
            }
        }

        return result.get(rowIndex);
        
    }
}
