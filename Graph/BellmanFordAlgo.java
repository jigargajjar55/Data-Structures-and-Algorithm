package Graph;

import java.util.*;

public class BellmanFordAlgo {

    public static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        int maxi = 1_00_000_000;
        Arrays.fill(dist, maxi);
        dist[S] = 0;

        // Go through all edges V-1 times
        for (int node = 0; node < V - 1; node++) {
            for (int it = 0; it < edges.size(); it++) {
                int u = edges.get(it).get(0);
                int v = edges.get(it).get(1);
                int wt = edges.get(it).get(2);

                if (dist[u] != maxi && (dist[u] + wt < dist[v])) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Checking if graph has any negative edges or negative cycle present
        for (int it = 0; it < edges.size(); it++) {
            int u = edges.get(it).get(0);
            int v = edges.get(it).get(1);
            int wt = edges.get(it).get(2);

            if (dist[u] != maxi && (dist[u] + wt < dist[v])) {
                return (new int[] { -1 });
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
