package Algorithms.Intervals;

import java.util.*;

import Algorithms.Intervals.MeetingRoomII_LC2406.Pair;

public class MeetingRoomII_LC2406 {
    
    //Instead of finding minimum groups, try to find maximum number of Intervals that are overlapping at certain point


   
    //Time: O(2 * ((N * log N) + N ))
    //Space: O(2 * N)
    public int minGroups(int[][] intervals) {

        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for(int i=0; i<n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int maxGroup = 0;
        int groups = 0;
        int ptr1 = 0;
        int ptr2 = 0;

        while(ptr1 < n){

            //Overlapping criteria
            if(start[ptr1] <= end[ptr2]){

                groups += 1;
                ptr1 += 1;

            }else{
                groups -= 1;
                ptr2 += 1;
            }

            maxGroup = Math.max(maxGroup, groups);
        }

        return maxGroup;

        
    }


    //Bruteforce approach
    class Pair{
        int leavingTime;
        int groupNumber;
        Pair(int leavingTime, int groupNumber){
            this.leavingTime = leavingTime;
            this.groupNumber = groupNumber;
        }
    }
    //Time: O(2 * (N * log N))
    //Space: O(2 * N)
    public int minGroups1(int[][] intervals) {

        int n = intervals.length;
        int totalGroups = n;

        //Sort an array
        Arrays.sort(intervals, (i1,i2) -> Integer.compare(i1[0],i2[0]));

        PriorityQueue<Pair> usedGroup = new PriorityQueue<>((p1,p2) -> Integer.compare(p1.leavingTime, p2.leavingTime));

        PriorityQueue<Integer> availableGroup = new PriorityQueue<>((p1,p2) -> Integer.compare(p1,p2));

        for(int i=0; i<n; i++){
            availableGroup.offer(i);
        }

        int result = 0;

        for(int i=0; i<n; i++){

            int[] interval = intervals[i];

            while(!usedGroup.isEmpty() && usedGroup.peek().leavingTime < interval[0]){
                int freeGroup = usedGroup.poll().groupNumber;
                availableGroup.offer(freeGroup);
            }


            int group = availableGroup.poll();
            usedGroup.offer(new Pair(interval[1], group));

            int ans = totalGroups - availableGroup.size();

            if(result < ans){
                result = ans;
            }
        }

         

        return result;
        
    }
}