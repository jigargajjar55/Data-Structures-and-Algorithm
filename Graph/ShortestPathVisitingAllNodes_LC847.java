import java.util.*;

public class ShortestPathVisitingAllNodes_LC847 {

    /*
     * 
    https://leetcode.com/problems/shortest-path-visiting-all-nodes/solutions/549233/breadth-first-search-bfs-with-intuitive-approach-thinking-process-13-ms/?envType=daily-question&envId=2023-09-17 
     * 
     * 
     * Time: O(N * (2 ^ N)), where nnn is the number of nodes. This is because there
             are 2^n possible subsets of nodes and n nodes to consider for each subset.
     * Space: O(N * (2 ^ N)), needed for the visited array and the queue.
     */

    private class Pair {
        int mask;
        int lead;

        Pair(int mask, int lead) {
            this.mask = mask;
            this.lead = lead;
        }
    }

    private int setBit(int mask, int lead) {
        return (mask | (1 << lead));
    }

    public int shortestPathLength(int[][] graph) {

        int nodes = graph.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            int u = i;
            adj.put(u, new ArrayList<>());

            for (int nbr : graph[i]) {
                adj.get(u).add(nbr);
            }
        }

        int rows = (int) (Math.pow(2, nodes));
        int cols = nodes;
        Queue<Pair> q = new LinkedList<>();

        int[][] visited = new int[rows][cols];

        // Add all nodes as source node to run BFS simulatenously
        for (int i = 0; i < nodes; i++) {
            int lead = i;
            int mask = setBit(0, lead);

            q.offer(new Pair(mask, lead));

        }

        int minPath = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Pair top = q.peek();
                q.poll();

                int lead = top.lead;
                int mask = top.mask;

                if (mask == rows - 1) {
                    return minPath;
                }

                if (adj.containsKey(lead)) {
                    for (int nbr : adj.get(lead)) {
                        int nbrLead = nbr;
                        int nbrMask = setBit(mask, nbrLead);

                        if (visited[nbrMask][nbrLead] == 1) {
                            continue;
                        }

                        visited[nbrMask][nbrLead] = 1;
                        q.offer(new Pair(nbrMask, nbrLead));
                    }
                }
            }

            minPath++;
        }

        return -1;
    }
}