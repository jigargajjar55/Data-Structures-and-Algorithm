
import java.util.*;

class Pair32 {
    int dist;
    int node;

    Pair32(int dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

public class ShortestPathInUndirectedWeightedGraph {

    // Dijkstra's Algorithm : Time: O(E * log(N)) + O(N) Space: O(N)
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {

        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(new int[] { v, wt });

            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(new int[] { u, wt });
        }

        // boolean[] visited = new boolean[n+1];
        int[] distance = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            distance[i] = (int) (1e9);
        }
        PriorityQueue<Pair32> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.dist, p2.dist));
        pq.offer(new Pair32(0, 1));
        distance[1] = 0;
        // visited[1] = true;

        while (!pq.isEmpty()) {

            Pair32 top = pq.peek();
            pq.poll();
            int topdist = top.dist;
            int topnode = top.node;

            if (adj.containsKey(topnode)) {
                for (int[] nbr : adj.get(topnode)) {
                    int adjdist = nbr[1];
                    int adjnode = nbr[0];

                    if (topdist + adjdist < distance[adjnode]) {

                        distance[adjnode] = topdist + adjdist;
                        pq.offer(new Pair32(topdist + adjdist, adjnode));

                        parent[adjnode] = topnode;
                    }
                }
            }

        }
        List<Integer> result = new ArrayList<>();

        if (distance[n] == (int) (1e9)) {
            result.add(-1);
            return result;
        }

        int node = n;
        while (parent[node] != node) {
            result.add(node);
            node = parent[node];
        }
        result.add(1);

        Collections.reverse(result);

        return result;
    }
}
