package Patterns.Intervals;

import java.util.*;

public class InsertInterval_LC57 {

    // Greedy Approach
    // Use Priority Queue for getting intervals which has small starting time
    // We will make one list and apply merge overlapping intervals logic
    // Time: O(N * log N)
    // Space: O(2 * N)
    public int[][] insert1(int[][] intervals, int[] newInterval) {

        List<int[]> updatedList = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[0], i2[0]));

        for (int[] it : intervals) {
            pq.offer(it);
        }
        pq.offer(newInterval);
        while (!pq.isEmpty()) {
            int[] front = pq.poll();
            updatedList.add(front);
        }

        int[] top = updatedList.get(0);

        List<int[]> result = new ArrayList<>();

        for (int i = 1; i < updatedList.size(); i++) {

            if (top[1] >= updatedList.get(i)[0]) {
                top[1] = Math.max(top[1], updatedList.get(i)[1]);
            } else {
                result.add(top);
                top = updatedList.get(i);
            }
        }

        result.add(top);

        return result.toArray(new int[result.size()][2]);
    }

    // We will create one list and add all intervals and new interval
    // We will sort the list based on starting time
    // Then we will apply merge overlapping interval logic
    // Time: O(N * log N)
    // Space: O(N)
    public int[][] insert2(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        List<int[]> intervalList = new ArrayList<>();
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            intervalList.add(intervals[i]);
        }
        intervalList.add(newInterval);

        Collections.sort(intervalList, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        int size = intervalList.size();
        int[] top = intervalList.get(0);

        for (int i = 1; i < size; i++) {

            int[] curr = intervalList.get(i);

            if (top[1] >= curr[0]) {
                top[1] = Math.max(top[1], curr[1]);
            } else {
                result.add(top);
                top = curr;
            }
        }

        result.add(top);

        return result.toArray(new int[result.size()][2]);

    }

    //Optimise approach
    // Time: O(N)
    // Space: O(N){For Result list}
    public int[][] insert3(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        int itr = 0;

        // First will add all intervals which ending time is less than starting time of
        // newInterval
        while (itr < n && intervals[itr][1] < newInterval[0]) {
            result.add(intervals[itr]);
            itr++;
        }

        // Then we will check if newInterval is overlapping or not
        // If it is overlapped, we will merge it till we found overlapping interval
        while (itr < n && newInterval[1] >= intervals[itr][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[itr][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[itr][1]);
            itr++;
        }

        // After loop, We will get interval which is merged or non-overlapping
        result.add(newInterval);

        // Check if there any remaining intervals are there or not
        // If yes, we will add into list
        while (itr < n) {
            result.add(intervals[itr]);
            itr++;
        }

        // Return the array by converting to 2D Array
        return result.toArray(new int[result.size()][2]);
    }

}
