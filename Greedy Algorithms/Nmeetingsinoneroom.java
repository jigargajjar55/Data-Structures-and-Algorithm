import java.util.*;

//Time: O(N + (N * log (N)) + N), Space: O(N * 2)
public class Nmeetingsinoneroom {
    // Function to find the maximum number of meetings that can
    // be performed in a meeting room.

    public static int maxMeetings(int start[], int end[], int n) {
        int[][] events = new int[n][2];
        for (int i = 0; i < n; i++) {
            events[i][0] = start[i];
            events[i][1] = end[i];
        }

        Arrays.sort(events, (e1, e2) -> Integer.compare(e1[1], e2[1]));

        int endDay = events[0][1];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (endDay < events[i][0]) {
                count++;

                endDay = events[i][1];
            }
        }

        return count;
    }
}