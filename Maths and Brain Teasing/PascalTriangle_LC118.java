import java.util.*;

public class PascalTriangle_LC118 {
    

    //Time: O(N ^ 2), Space: O(N ^ 2)
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> startingList = new ArrayList<>();
        startingList.add(1);
        result.add(startingList);

        for(int row = 1; row < numRows; row++){

            List<Integer> ans = new ArrayList<>();
            int n = result.get(row-1).size();

            for(int j=0; j<n; j++){

                if(j == 0){
                    ans.add(1);
                }
                if(j > 0 && j < n){
                    ans.add(result.get(row-1).get(j-1) + result.get(row-1).get(j));
                }     
                if(j == n-1){
                    ans.add(1);
                }           

            }

            result.add(ans);
        }

        return result;
        
    }
}