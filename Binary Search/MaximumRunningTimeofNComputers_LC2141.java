import java.util.*;

public class MaximumRunningTimeofNComputers_LC2141 {
    
    //Time: O(N * log(sum of all batteries time)), Space: O(1)
    private boolean isPossible(int[] batteries,long time,int computers){

        long totalTime = time * computers;

        for(int batteryTime : batteries){
            totalTime -= Math.min(time,batteryTime);
        }

        if(totalTime <= 0){
            return true;
        }else{
            return false;
        }

    }
    public long maxRunTime(int n, int[] batteries) {

        long start = 0, end = 0;

        for(int battery : batteries){
            end += battery;
        }

        long result = 0;

        while(start <= end){

            long mid = start + ((end-start)/2);

            if(isPossible(batteries,mid,n)){
                result = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }

        return result;
        
    }
}
