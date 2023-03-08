package Graph;

import java.util.*;

public class ArticulationPointPractice {

    public static void dfs(int node, int parent, int timer, int[] disc, int[] low, boolean[] visited,
            Map<Integer, List<Integer>> adj, ArrayList<Integer> result) {
        visited[node] = true;
        disc[node] = timer;
        low[node] = timer;
        timer++;

        int child = 0;

        if (adj.containsKey(node)) {

            List<Integer> neighbours = adj.get(node);

            for (int neighbour : neighbours) {

                if (neighbour == parent) {
                    continue;
                }

                if (!visited[neighbour]) {

                    dfs(neighbour, node, timer, disc, low, visited, adj, result);

                    low[node] = Math.min(low[node], low[neighbour]);

                    // condition for Articulation point
                    if (low[neighbour] >= disc[node] && parent != -1) {
                        result.set(node, 1);

                    }
                    child++;

                } else {
                    low[node] = Math.min(low[node], disc[neighbour]);
                }

            }
        }
        if (parent == -1 && child > 1) {
            result.set(node, 1);
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int e = 7;

        ArrayList<int[]> edges = new ArrayList<>();
        edges.add(new int[] { 0, 1 });
        edges.add(new int[] { 0, 2 });
        edges.add(new int[] { 1, 2 });
        edges.add(new int[] { 1, 3 });
        edges.add(new int[] { 1, 4 });
        edges.add(new int[] { 3, 5 });
        edges.add(new int[] { 4, 5 });

        // Create adj list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < edges.size(); i++) {

            int u = edges.get(i)[0];
            int v = edges.get(i)[1];

            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(v);

            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(u);

        }

        int timer = 0;
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];

        ArrayList<Integer> articulationPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            articulationPoints.add(0);
        }

        for (int i = 0; i < n; i++) {
            disc[i] = -1;
            low[i] = -1;
        }

        // Do DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, timer, disc, low, visited, adj, articulationPoints);
            }
        }

        for (int i = 0; i < n; i++) {
            if (articulationPoints.get(i) > 0) {
                System.out.print(i + "  ");
            }
        }

    }

}
