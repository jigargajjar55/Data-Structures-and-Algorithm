package Graph;

import java.util.*;

public class CountNumberOfCompleteComponentsLC2685 {

    private void dfs(int node, Map<Integer, List<Integer>> adj, boolean[] visited, int[] nodes, int[] edgeCount) {

        visited[node] = true;
        nodes[0] += 1;

        if (adj.containsKey(node)) {
            edgeCount[0] += adj.get(node).size();

            for (int nbr : adj.get(node)) {
                if (!visited[nbr]) {
                    dfs(nbr, adj, visited, nodes, edgeCount);
                }
            }
        }

    }

    public int countCompleteComponents(int n, int[][] edges) {

        // Create adj list
        // Time: O(E + (N + 2E)), Space:O(N + 2E)
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        int count = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] nodes = new int[1];
                int[] edgeCount = new int[1];

                dfs(i, adj, visited, nodes, edgeCount);

                if (nodes[0] * (nodes[0] - 1) == edgeCount[0]) {
                    count++;
                }

            }
        }

        return count;

    }
}
