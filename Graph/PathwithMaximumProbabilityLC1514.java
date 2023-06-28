import java.util.*;

class Pair {
    int node;
    double prob;

    Pair(int node, double prob) {
        this.node = node;
        this.prob = prob;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Time: O(E * log N), Space: O(N + 2E)
        Map<Integer, List<Pair>> adj = new HashMap<>();
        int edgesLength = edges.length;
        for (int i = 0; i < edgesLength; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];

            if (!adj.containsKey(u)) {
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(new Pair(v, prob));

            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(new Pair(u, prob));
        }
        double[] probs = new double[n];
        probs[start] = 1;

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Double.compare(p2.prob, p1.prob));
        pq.offer(new Pair(start, probs[start]));

        while (!pq.isEmpty()) {
            Pair topPair = pq.peek();
            pq.poll();

            int node = topPair.node;
            double currProb = topPair.prob;

            if (node == end) {
                return currProb;
            }

            if (adj.containsKey(node)) {
                for (Pair nbr : adj.get(node)) {
                    double nbrProb = currProb * nbr.prob;

                    if (nbrProb > probs[nbr.node]) {
                        probs[nbr.node] = nbrProb;
                        pq.offer(new Pair(nbr.node, probs[nbr.node]));
                    }
                }
            }
        }

        return 0;
    }
}