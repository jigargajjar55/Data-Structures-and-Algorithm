package BinarySearch;
import java.util.*;

public class MinimumSpeedToArriveonTime_LC1870 {
    
    //Time: O(N * log N), Space: O(1) {N = (10 ^ 7)}
    private boolean isPossible(int mid,int n,int[] dist,double hour){
        double time = 0;

        for(int i=0; i<n; i++){
            //Get ceil of Time so it will be in integer hours
            time = Math.ceil(time);

            //Calculate current Time
            double currTime = (double)dist[i]/(double)mid;

           // System.out.println(mid +" " + currTime + " " +  correctTime);

            if(time + currTime <= hour){
                time += currTime;
            }else{
                return false;
            }            
        } 

        return true;

    }
    public int minSpeedOnTime(int[] dist, double hour) {

        int n = dist.length;
        int start = 1;
        int end = (int)(1e7);

        int ans = -1;

        while(start <= end){

            int mid = start + ((end-start)/2);

            if(isPossible(mid,n,dist,hour)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return ans;        
    }
}