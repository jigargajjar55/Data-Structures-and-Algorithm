package Queue;

import java.util.*;

public class Dota2SenateLC {

    // https://leetcode.com/problems/dota2-senate/solutions/3483399/simple-diagram-explanation/?orderBy=most_votes
    // Approach :
    // It's follow Round-robin order, so we are going to use Queues.
    // We will use 2 queues and compare with there index to perform actions

    // Time : O(N), Space: O(2 * N){2 Queues}
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char ch = senate.charAt(i);

            if (ch == 'R') {
                rad.offer(i);
            } else {
                dire.offer(i);
            }
        }

        while (!rad.isEmpty() && !dire.isEmpty()) {
            int radIndex = rad.peek();
            rad.poll();
            int direIndex = dire.peek();
            dire.poll();

            if (radIndex < direIndex) {
                rad.offer(radIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }

        if (rad.isEmpty()) {
            return "Dire";
        } else {
            return "Radiant";
        }

    }
}
