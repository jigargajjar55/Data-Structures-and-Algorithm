
import java.util.*;

class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class CycleDetectionInUndirectedGraph {

    private boolean isCycleBFS(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(node, -1));
        visited[node] = true;

        while (!q.isEmpty()) {
            Pair front = q.poll();
            int frontNode = front.first;
            int parentNode = front.second;

            if (adj.get(frontNode).size() > 0) {
                for (int nbr : adj.get(frontNode)) {

                    if (!visited[nbr]) {
                        q.offer(new Pair(nbr, frontNode));
                        visited[nbr] = true;
                    } else if (parentNode != nbr) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];

        for (int node = 0; node < V; node++) {

            if (!visited[node]) {
                boolean ans = isCycleBFS(node, adj, visited);
                if (ans) {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
