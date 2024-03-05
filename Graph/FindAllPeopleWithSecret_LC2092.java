import java.util.*;

class FindAllPeopleWithSecret_LC2092 {
    class Pair {
        int person;
        int time;

        Pair(int person, int time) {
            this.person = person;
            this.time = time;
        }
    }

    // Time: O(N + 2E){E: No. of meetings}
    // Space: O(N + 2E)
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // Multi-Source BFS
        // since the initial queue contains more than one element
        int m = meetings.length;
        List<Integer> result = new ArrayList<>();

        Map<Integer, List<Pair>> adj = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int first = meetings[i][0];
            int second = meetings[i][1];
            int time = meetings[i][2];

            if (!adj.containsKey(first)) {
                adj.put(first, new ArrayList<>());
            }
            adj.get(first).add(new Pair(second, time));

            if (!adj.containsKey(second)) {
                adj.put(second, new ArrayList<>());
            }
            adj.get(second).add(new Pair(first, time));
        }

        Queue<Integer> q = new LinkedList<>();
        int[] earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);

        // NOTE:
        // Here, we may process a node again.
        // Hence, we dont maintain a visited array

        // Since, the 0th person knows the secret at t=0
        q.offer(0);
        earliest[0] = 0;
        // and so does the first person.
        q.offer(firstPerson);
        earliest[firstPerson] = 0;

        while (!q.isEmpty()) {

            int person = q.poll();

            if (adj.containsKey(person)) {

                for (Pair next : adj.get(person)) {

                    int nextPerson = next.person;
                    int nextmeetingTime = next.time;

                    // we will add the node only when the
                    // time of the nxt person is after the time of person(parent node) and
                    // earliest[nextPerson] > t, i.e:- the previously learned time of the secret
                    // should
                    // be greater than the curr time

                    if (earliest[person] <= nextmeetingTime && earliest[nextPerson] > nextmeetingTime) {
                        earliest[nextPerson] = nextmeetingTime;
                        q.offer(nextPerson);
                    }
                }

            }

        }

        for (int i = 0; i < n; i++) {
            if (earliest[i] != Integer.MAX_VALUE) {
                result.add(i);
            }
        }
        return result;

    }
}
