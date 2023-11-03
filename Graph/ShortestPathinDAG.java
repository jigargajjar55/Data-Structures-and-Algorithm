
import java.util.*;

//User function Template for Java
 class ShortestPathinDAG {

    private void topoSort(int node, Map<Integer, List<int[]>> adj, boolean[] visited, Stack<Integer> st) {

        visited[node] = true;

        if (adj.containsKey(node)) {
            for (int[] nbr : adj.get(node)) {
                if (!visited[nbr[0]]) {
                    topoSort(nbr[0], adj, visited, st);
                }
            }
        }

        st.push(node);

    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(new int[] { v, wt });
        }

        // Get tops sort
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                topoSort(i, adj, visited, st);
            }
        }

        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = (int) (1e9);
        }

        dist[0] = 0;

        while (!st.isEmpty()) {
            int node = st.peek();
            st.pop();

            if (adj.containsKey(node)) {
                for (int[] nbr : adj.get(node)) {
                    int v = nbr[0];
                    int wt = nbr[1];

                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }

        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) (1e9)) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}

class GraphDAG {
    Map<Integer, List<int[]>> adj;

    GraphDAG() {
        adj = new HashMap<>();
    }

    void addEdge(int u, int v, int weight) {
        int[] p = { v, weight };
        if (!adj.containsKey(u)) {
            adj.put(u, new ArrayList<>());
        }
        adj.get(u).add(p);
    }

    void printAdj() {
        for (int key : adj.keySet()) {
            System.out.print(key + " -> ");

            for (int[] p : adj.get(key)) {
                System.out.print("[" + p[0] + ", " + p[1] + "], ");
            }

            System.out.println();
        }
    }

    void getTopological(int node, boolean[] visited, Stack<Integer> st, Map<Integer, List<int[]>> adj) {
        visited[node] = true;

        if (adj.containsKey(node)) {

            for (int[] neighbour : adj.get(node)) {

                if (!visited[neighbour[0]]) {
                    getTopological(neighbour[0], visited, st, adj);
                }
            }
        }

        st.push(node);
    }

    void dfs(int node, boolean[] visited, Stack<Integer> st) {
        getTopological(node, visited, st, adj);
    }

    void getShortestDist(int src, int[] dist, Stack<Integer> st) {

        dist[src] = 0;

        while (!st.isEmpty()) {
            int top = st.pop();

            if (dist[top] != Integer.MAX_VALUE && adj.containsKey(top)) {

                for (int[] neighbour : adj.get(top)) {

                    if (dist[top] + neighbour[1] < dist[neighbour[0]]) {
                        dist[neighbour[0]] = dist[top] + neighbour[1];
                    }

                }
            }

        }
    }

}

 class ShortestPathinDAG2 {

    public static void main(String[] args) {
        GraphDAG g = new GraphDAG();

        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 6);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        g.printAdj();
        int nodes = 6;
        // First Step: Get Topological sort using DFS
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                g.dfs(0, visited, st);
            }
        }

        int[] shortestDist = new int[nodes];
        Arrays.fill(shortestDist, Integer.MAX_VALUE);

        int src = 1;
        g.getShortestDist(src, shortestDist, st);
        // Printing answer of Shortest Dist of all nodes from Source 1 node
        System.out.println(Arrays.toString(shortestDist));

    }

}
