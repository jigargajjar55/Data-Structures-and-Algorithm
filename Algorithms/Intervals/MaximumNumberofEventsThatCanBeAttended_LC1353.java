package Algorithms.Intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberofEventsThatCanBeAttended_LC1353 {
    //Time: O((N + T) * log N){N: no. of Events, T: Range of days}
    //Space: O(N + log N)
    public int maxEvents(int[][] events) {

        int len = events.length;
        //Sort an Array based on starting time
        Arrays.sort(events, (a,b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int startDay = Integer.MAX_VALUE;
        int endDay = -1;

        for(int[] event : events){
            startDay = Math.min(startDay, event[0]);
            endDay = Math.max(endDay, event[1]);
        }

        int result = 0;
        int index = 0;
        for(int day=startDay; day<=endDay; day++){

            while(index < len && events[index][0] == day){
                pq.offer(events[index][1]);
                index += 1;
            }

            while(!pq.isEmpty() && pq.peek() < day){
                pq.poll();
            }

            if(!pq.isEmpty()){
                result += 1;
                pq.poll();
            }
        }

        return result;
    }
}
