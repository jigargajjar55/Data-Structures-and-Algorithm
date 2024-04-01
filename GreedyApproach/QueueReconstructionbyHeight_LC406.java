package GreedyApproach;

import java.util.*;

public class QueueReconstructionbyHeight_LC406 {

    // Time: O(N * log N)
    // Space: O(N)
    public int[][] reconstructQueue(int[][] people) {

        int n = people.length;

        Arrays.sort(people, (p1, p2) -> (p1[0] == p2[0])
                ? Integer.compare(p1[1], p2[1])
                : Integer.compare(p2[0], p1[0]));

        List<int[]> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            result.add(people[i][1], people[i]);
        }

        return result.toArray(new int[result.size()][2]);

    }
}