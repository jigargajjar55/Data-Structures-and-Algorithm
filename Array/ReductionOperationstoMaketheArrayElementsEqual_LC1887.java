package Array;
import java.util.*;

public class ReductionOperationstoMaketheArrayElementsEqual_LC1887 {
    

    

    //Time: O((N * log N) + N)
    //Space: O(1)
    public int reductionOperations(int[] nums) {


        int n = nums.length;
        Arrays.sort(nums);

        int minOps = 0; 


        for(int i=n-1; i>0; i--){

            if(nums[i] != nums[i-1]){
                minOps += (n - i);
            }

        }

        return minOps;
    }






   //Time: O(N + (N * log N) + N + (~N))
   //Space: O(2 * N)
    public int reductionOperations1(int[] nums) {

        int n = nums.length;
        Integer[] numsArray = new Integer[n];
        for(int i=0; i<n; i++){
            numsArray[i] = nums[i];
        }

        //Sort an Array
        Arrays.sort(numsArray, Collections.reverseOrder());

        List<Integer> numList = new ArrayList<>();

        int prev = -1;

        for(int i=0; i<n; i++){

            if(prev != numsArray[i]){
                numList.add(1);
                prev = numsArray[i];                
            }else{
                int size = numList.size();
                int count = numList.get(size-1);
                numList.set(size-1,count+1);
            }

           
        }

        int opCount = 0;
        int top = numList.get(0);
        int size = numList.size();

        for(int i=1; i<size; i++){

            int count = numList.get(i);            
            numList.set(i,top + count);

            opCount += top; 
            top += count;
        }

        return opCount;
    }
}