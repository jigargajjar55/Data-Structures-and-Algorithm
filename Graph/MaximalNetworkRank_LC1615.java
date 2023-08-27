import java.util.*;

public class MaximalNetworkRank_LC1615 {

    // https://leetcode.com/problems/maximal-network-rank/solutions/889162/c-straightforward-graph-with-brief-explanation-o-n-runtime/

    //Time: O(R + (N * N)), Space: O(N + (N + 2E)) {Degree Array and Adjcency list, R: Length of roads array, N: no. Of Nodes}
    public int maximalNetworkRank(int n, int[][] roads) {

        int[] degree = new int[n];
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        int roadArrayLength = roads.length;

        for(int i=0; i<roadArrayLength; i++){

            int u = roads[i][0];
            int v = roads[i][1];

            if(!adj.containsKey(u)){
                adj.put(u, new HashSet<>());
            }
            adj.get(u).add(v);
            degree[u]++;

            if(!adj.containsKey(v)){
                adj.put(v, new HashSet<>());
            }
            adj.get(v).add(u);
            degree[v]++;
        }

        int maxRank = -(int)(1e9);

        for(int node=0; node<n; node++){

            for(int nbr=node+1; nbr<n; nbr++){

                int rank = degree[node] + degree[nbr];

                if(adj.containsKey(node) && adj.get(node).contains(nbr)){
                    rank -= 1;
                }

                //System.out.println(node + " " + nbr + " " + rank);

                if(maxRank < rank){
                    maxRank = rank;
                }               

            }           
        }

        return maxRank;        
    }
}