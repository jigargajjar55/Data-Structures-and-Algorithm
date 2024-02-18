
import java.util.*;

public class FurthestBuildingYouCanReach_LC1642 {

    // Time: O(N * log N)
    // Space: O(N)
    /*
     * Be greedy and use bricks for each gap, but also maintain a priority queue
     * (reverse order) for each gap.
     * When you run out of bricks, replace the highest gap with a ladder, gaining
     * those bricks back.
     * Continue until you run out of ladders and bricks.
     * 
     */

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2, p1));

        int n = heights.length;

        for (int index = 0; index < n - 1; index++) {

            if (heights[index] >= heights[index + 1]) {
                continue;
            }

            bricks -= (heights[index + 1] - heights[index]);

            pq.offer(heights[index + 1] - heights[index]);

            if (bricks < 0) {
                bricks += pq.poll();

                if (ladders > 0) {
                    ladders -= 1;
                } else {
                    return index;
                }
            }
        }

        return (n - 1);

    }
}