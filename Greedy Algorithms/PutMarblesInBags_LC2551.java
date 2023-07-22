import java.util.*;

public class PutMarblesInBags_LC2551 {
 

    // https://leetcode.com/problems/put-marbles-in-bags/solutions/3735599/bestest-explanation-you-have-ever-encountered/
    
    //Time: O(N * log N), Space: O(2 * K)
    
    public long putMarbles(int[] weights, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((p1,p2) -> Integer.compare(p2,p1));

        int n = weights.length;

        for(int i=0; i<n-1; i++){
            int adjSum = weights[i] + weights[i+1];

            minHeap.offer(adjSum);
            maxHeap.offer(adjSum);

            if(minHeap.size() > k - 1){
                minHeap.poll();
                maxHeap.poll();
            }
        }

        long maxScore = 0;
        long minScore = 0;

        while(!minHeap.isEmpty()){
            maxScore += minHeap.peek();
            minScore += maxHeap.peek();

            minHeap.poll();
            maxHeap.poll();
        }

        long ans = maxScore - minScore;

        return ans;

        
    }
}