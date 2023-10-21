import java.util.*;

public class ParallelCoursesIII_LC2050 {

    
    private int dfs(int node,int[] time,Map<Integer, List<Integer>> adj,int[] dp){

        //Overlapping sub-problem
        if(dp[node] != -1){
            return dp[node];
        }

        int currTime = time[node-1];

        int nbrTime = 0;

        if(adj.containsKey(node)){

            for(int nbr : adj.get(node)){

                int ans = dfs(nbr,time,adj,dp);
                nbrTime = Math.max(nbrTime,ans);
            }
        }

        int totalTime = currTime + nbrTime;

        return dp[node] = totalTime;

    }
    //Time: O(R + (N + E))),
    //Space: O(3N + E){Adjecency list, indegree and DP Array}
    public int minimumTime(int n, int[][] relations, int[] time) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n+1];

        int rLength = relations.length;
        for(int i=0; i<rLength; i++){
            int u = relations[i][1];
            int v = relations[i][0];

            if(!adj.containsKey(u)){
                adj.put(u, new ArrayList<>());
            } 
            adj.get(u).add(v);
            indegree[v]++;
        }

        int resultTime = -1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        for(int node=1; node<=n; node++){

            if(indegree[node] == 0){

                int ans = dfs(node,time,adj,dp);

                resultTime = Math.max(resultTime, ans);
            }
        }
        return resultTime;
    }
}