import java.util.*;
public class NonOverlappingIntervals_LC435 {
  
    //Time: O( N + (N * log N)), space: O(2)
    public int eraseOverlapIntervals(int[][] intervals) {

        //Sort the intervals
        Arrays.sort(intervals, (i1,i2) -> Integer.compare(i1[0],i2[0]));

       
        int[] top = intervals[0];
        int n = intervals.length;
        int count = 0;

        for(int i=1; i<n; i++){
            
            if(top[1] > intervals[i][0]){            

                count++;
                top[1] = Math.min(top[1], intervals[i][1]);               

            }else{
                top = intervals[i];
            }
        }

        return count;        
    }
}