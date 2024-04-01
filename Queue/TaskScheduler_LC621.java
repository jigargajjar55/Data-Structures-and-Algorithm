package Queue;

import java.util.*;

public class TaskScheduler_LC621 {

    class Pair {
        int count;
        int timeStamp;

        Pair(int count, int timeStamp) {
            this.count = count;
            this.timeStamp = timeStamp;
        }
    }

    // https://www.youtube.com/watch?v=s8p8ukTyA2I
    // Time: O(M * n * log 26)
    // Space: O(26)
    public int leastInterval(char[] tasks, int n) {

        int m = tasks.length;
        int[] freq = new int[26];
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.count, p1.count));

        for (int i = 0; i < m; i++) {
            freq[(int) (tasks[i] - 'A')]++;
        }

        for (int i = 0; i < 26; i++) {

            if (freq[i] > 0) {
                pq.offer(new Pair(freq[i], 0));
            }
        }

        int time = 0;
        Queue<Pair> q = new LinkedList<>();

        while (!pq.isEmpty() || !q.isEmpty()) {
            time += 1;

            if (!pq.isEmpty()) {
                Pair top = pq.poll();

                top.count -= 1;

                if (top.count > 0) {
                    q.offer(new Pair(top.count, time + n));
                }
            }

            if (!q.isEmpty()) {
                Pair top = q.peek();
                if (top.timeStamp == time) {
                    pq.offer(top);
                    q.poll();
                }
            }
        }

        return time;

    }
}
