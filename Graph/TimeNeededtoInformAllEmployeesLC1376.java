
import java.util.*;

class TimeNeededtoInformAllEmployeesLC1376 {

    private class Pair {
        int node;
        int time;

        Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    private int bfs(int n, int headID, Map<Integer, List<Integer>> adj, int[] informTime) {

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(headID, informTime[headID]));

        int noOfMin = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair top = q.peek();
                q.poll();

                int node = top.node;
                int time = top.time;

                if (adj.containsKey(node)) {
                    for (int nbr : adj.get(node)) {
                        q.offer(new Pair(nbr, time + informTime[nbr]));
                    }
                } else {
                    noOfMin = Math.max(noOfMin, time);
                }
            }
        }

        return noOfMin;
    }

    private int dfs(int node, Map<Integer, List<Integer>> adj, int[] informTime) {

        int time = 0;

        if (adj.containsKey(node)) {
            for (int nbr : adj.get(node)) {

                int currTime = informTime[node] + dfs(nbr, adj, informTime);

                time = Math.max(time, currTime);
            }
        }

        return time;

    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Time: O(N + N), Space:O(N + N) {Adjecency list, Queue and Aux.Stack space}
        // For both solution, time complexity will be O(N) because no node will repeat
        // itself in grpah.
        // Creating Adjacency list
        for (int i = 0; i < n; i++) {

            if (manager[i] == -1) {
                continue;
            }

            if (!adj.containsKey(manager[i])) {
                adj.put(manager[i], new ArrayList<>());
            }

            adj.get(manager[i]).add(i);
        }

        // return bfs(n, headID, adj, informTime);

        return dfs(headID, adj, informTime);

    }
}
