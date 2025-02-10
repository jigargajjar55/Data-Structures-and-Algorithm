package Tree.BinaryTree;
import java.util.*;

public class BinaryTreesWithFactors_LC823 {
    

    
    private long addMod(long a, long b){
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }
    
    //Time: O((N * log N) + (N ^ 2))
    //Space: O(N){For HashMap}
    public int numFactoredBinaryTrees(int[] arr) {

        int n = arr.length;
        
        //First Step:
        //Sort a given array
        //We can traverse the array and make sure that selected number have factors in left side
        Arrays.sort(arr);
        //Taking HashMap for each number and storing values as long because it might go overflow as we are doing multiplication
        Map<Integer, Long> map = new HashMap<>();

        //Consider first element as a first Binary tree and initialize it ans to 1
        //We will maintain HashMap to get count of binary tree possible for number
        long ans = 1;
        map.put(arr[0],ans);

        //We will loop through array elements
        for(int i=1; i<n; i++){
            
            //Single element will consider as a valid binary tree, So we always consider for each number
            long sum = 1;

            //Loop thorugh left part of array
            //Will try to get factors for current number
            for(int j=0; j<i; j++){

                //If we get factors by checking modulo as well as checking other number present
                //Will try to sum it up all possible combination
                if((arr[i] % arr[j] == 0) && map.containsKey(arr[i] / arr[j])){
                    sum = addMod(sum, (map.get(arr[i]/arr[j]) * map.get(arr[j])));
                }
            }

            //We will add this possible sum value in hashmap for current number
            map.put(arr[i], sum); 

            //We will also add sum value in ans for each number
            ans = addMod(ans, sum);      
           

        }

        //We will return ans by type casting and doing module operation
        return (int)(ans % 1_000_000_007);


        
    }
}
