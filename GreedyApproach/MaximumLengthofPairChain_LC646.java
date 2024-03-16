package GreedyApproach;
import java.util.*;

public class MaximumLengthofPairChain_LC646 {
    

    //Time: O((N * log N) + N), Space: O(1)
    //Sorting approach
    public int findLongestChain1(int[][] pairs) {

        int n = pairs.length;
        //Consider pairs as jobs, with [start time, end time],
        //Then the problem is converted to ask the maximum jobs we can accomplish.
        //We will consider those pair which can be finish earlier, so we sorted based on ending time
        Arrays.sort(pairs, (p1,p2) -> Integer.compare(p1[1],p2[1]));

        int chainLength = 0;
        int i = 0;

        while(i < n){
            chainLength++;

            int currEnd = pairs[i][1];

            //Checking for pair if it overlapping or not.
            //If its overlapping, we will skip it 
            while(i < n && pairs[i][0] <= currEnd){
                i++;
            }
        }

        return chainLength;        
    }


    //Time: O(N * log N), Space: O(N){Min. Heap}
    //Min-Heap approach
    public int findLongestChain2(int[][] pairs) {

        int n = pairs.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> Integer.compare(p1[1], p2[1]));

        for(int i=0; i<n; i++){
            pq.offer(new int[]{pairs[i][0], pairs[i][1]});
        }


        int pairLength = 0;

        while(!pq.isEmpty()){

            int[] top = pq.peek();
            pq.poll();

            pairLength++;

            while(!pq.isEmpty() && pq.peek()[0] <= top[1]){
                pq.poll();
            }
        }

        return pairLength;        
    }
}