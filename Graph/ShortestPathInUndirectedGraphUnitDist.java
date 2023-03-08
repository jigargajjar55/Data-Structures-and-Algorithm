package Graph;

import java.util.*;

public class ShortestPathInUndirectedGraphUnitDist {

    public int[] shortestPath(int[][] edges, int n, int m, int src) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(v);

            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(u);
        }

        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = (int) (1e9);
        }

        Queue<Integer> q = new LinkedList<>();
        // boolean[] visited = new boolean[n];
        q.offer(src);
        dist[src] = 0;
        // visited[src] = true;

        while (!q.isEmpty()) {
            int node = q.peek();
            q.poll();

            if (adj.containsKey(node)) {
                for (int nbr : adj.get(node)) {
                    if (dist[nbr] == (int) (1e9)) {
                        q.offer(nbr);
                        // visited[nbr] = true;
                        dist[nbr] = dist[node] + 1;
                    }

                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == (int) (1e9)) {
                dist[i] = -1;
            }
        }

        return dist;

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
