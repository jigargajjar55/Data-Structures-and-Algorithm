package GreedyApproach;
import java.util.*;

public class MeetingRoomsIII_LC2402 {

    class Pair {
        long endTime;
        int room;

        Pair(long endTime, int room) {
            this.endTime = endTime;
            this.room = room;
        }
    }

    // Time: O((M * log M) + N + (M * log N))
    // Space: O(N)
    public int mostBooked(int n, int[][] meetings) {

        int m = meetings.length;

        // Sort the meetings based on Starting time
        Arrays.sort(meetings, (m1, m2) -> Integer.compare(m1[0], m2[0]));

        // Add all available rooms in min Heap
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        // To count number of meeting can be held
        int[] meetingRooms = new int[n];

        // { meeting end, room taken } -> sort by end time and by room number
        PriorityQueue<Pair> usedRooms = new PriorityQueue<>(
                (p1, p2) -> (p1.endTime == p2.endTime) ? Integer.compare(p1.room, p2.room)
                        : Long.compare(p1.endTime, p2.endTime));

        for (int i = 0; i < m; i++) {

            long start = meetings[i][0];
            long end = meetings[i][1];

            // return room to available if meeting has ended
            // Will get all used room which meeting end Time is lesser than start time of
            // current meeting
            while (!usedRooms.isEmpty() && start >= usedRooms.peek().endTime) {
                Pair top = usedRooms.poll();
                availableRooms.offer(top.room);
            }

            // No room available for meeting, adjust the next meeting start time with delay
            if (availableRooms.isEmpty()) {
                Pair minEndMeeting = usedRooms.poll();
                availableRooms.offer(minEndMeeting.room);
                end = minEndMeeting.endTime + (end - start);
            }

            // schedule the meeting
            int room = availableRooms.poll();
            meetingRooms[room]++;
            usedRooms.offer(new Pair(end, room));
        }

        int minRoomIdx = 0;

        // find the most used room
        for (int room = 0; room < n; room++) {

            if (meetingRooms[minRoomIdx] < meetingRooms[room]) {
                minRoomIdx = room;
            }

        }

        return minRoomIdx;

    }
}