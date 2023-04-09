import java.util.*;

class Meeting {
    int start;
    int end;
    int pos;

    Meeting(int start, int end, int pos) {
        this.start = start;
        this.end = end;
        this.pos = pos;
    }
}

public class MaximumMeetingsinOneRoom {

    // Time: O(N + (N*logN) + N + (N*logN)), Space: O(N)
    public static ArrayList<Integer> maxMeetings(int N, int[] S, int[] F) {

        ArrayList<Integer> result = new ArrayList<Integer>();

        ArrayList<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            meetings.add(new Meeting(S[i], F[i], i + 1));
        }

        Collections.sort(meetings, (m1, m2) -> (m1.end == m2.end)
                ? Integer.compare(m1.pos, m2.pos)
                : Integer.compare(m1.end, m2.end));

        int endTime = meetings.get(0).end;
        result.add(meetings.get(0).pos);

        for (int i = 1; i < N; i++) {

            if (endTime < meetings.get(i).start) {
                result.add(meetings.get(i).pos);
                endTime = meetings.get(i).end;
            }

        }

        Collections.sort(result);

        return result;

    }
}