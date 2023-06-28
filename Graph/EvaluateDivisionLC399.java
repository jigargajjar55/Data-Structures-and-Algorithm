
import java.util.*;

class Solution {
    private Map<String, Map<String, Double>> makeGraph(List<List<String>> equations, double[] values) {

        Map<String, Map<String, Double>> adj = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);

            adj.putIfAbsent(u, new HashMap<>());
            adj.get(u).put(v, values[i]);

            adj.putIfAbsent(v, new HashMap<>());
            adj.get(v).put(u, (1 / values[i]));
        }

        return adj;

    }

    private double dfs(String u, String endNode, Map<String, Map<String, Double>> adj, double[] values,
            Set<String> visited) {

        // Not present
        if (!adj.containsKey(u)) {
            return -1.0;
        }

        // Present
        if (adj.get(u).containsKey(endNode)) {
            return adj.get(u).get(endNode);
        }

        visited.add(u);

        for (Map.Entry<String, Double> nbr : adj.get(u).entrySet()) {

            if (!visited.contains(nbr.getKey())) {
                double productWeight = dfs(nbr.getKey(), endNode, adj, values, visited);

                if (productWeight != -1.0) {
                    return (nbr.getValue() * productWeight);
                }

            }
        }

        return -1.0;

    }

    // Time: O( E + (Q * (N + 2E))), Space: O((N + 2E) + O(N))
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int n = queries.size();
        double[] result = new double[n];
        // Time: O(E), Space: O(N + 2E)
        Map<String, Map<String, Double>> adj = makeGraph(equations, values);

        // Time: O(Q * (N + 2E))
        for (int i = 0; i < queries.size(); i++) {
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);

            Set<String> visited = new HashSet<>();

            double ans = dfs(u, v, adj, values, visited);
            result[i] = ans;
        }

        return result;

    }
}