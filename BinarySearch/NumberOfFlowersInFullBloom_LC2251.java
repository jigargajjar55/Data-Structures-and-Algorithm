package BinarySearch;
import java.util.*;

public class NumberOfFlowersInFullBloom_LC2251 {
    

    //Time: O(log F) 
    private int binarySearchUpperBound(int[] arr,int target){
        int start = 0;
        int end = arr.length;

        while(start < end){
            int mid = start + ((end-start) / 2);

            if(arr[mid] <= target){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return start;
    }
    //Time: O(log F) 
    private int binarySearchLowerBound(int[] arr,int target){
        int start = 0;
        int end =  arr.length;

        while(start < end){
            int mid = start + ((end-start) / 2);

            if(arr[mid] < target){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return start;
    }
    //Time: O(F + (2 * (F * log F)) + (P * log F)))
    //Space: O(2 * F)
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {

        int fLength = flowers.length;
        int pLength = people.length;
        int[] result = new int[pLength];
        
        int[] start = new int[fLength];
        int[] stop = new int[fLength];

        for(int i=0; i<fLength; i++){
            start[i] = flowers[i][0];
            stop[i] = flowers[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(stop);

        for(int i=0; i<pLength; i++){
            int currTime = people[i];

            int startBlooming = binarySearchUpperBound(start, currTime);
            int stopBlooming = binarySearchLowerBound(stop, currTime);

            result[i] = startBlooming - stopBlooming;
        }

        return result;        
    }
}