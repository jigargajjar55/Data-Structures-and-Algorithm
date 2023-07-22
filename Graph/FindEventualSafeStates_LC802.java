import java.util.*;

public class FindEventualSafeStates_LC802 {

    //Time: O(2 * (N + E)), Space: O(2 * N + E)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] indegree = new int[n];
        Map<Integer, List<Integer>> transposeAdj = new HashMap<>();
        
        for(int node=0; node<n; node++){
            
            if(graph[node].length > 0){
                for(int nbr : graph[node]){
                    if(!transposeAdj.containsKey(nbr)){
                        transposeAdj.put(nbr, new ArrayList<>());
                    }
                    transposeAdj.get(nbr).add(node);
                    indegree[node]++;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int node=0; node<n; node++){
            if(indegree[node] == 0){
                q.offer(node);
            }
        }

        while(!q.isEmpty()){
            int top = q.peek();
            q.poll();
            result.add(top);

            if(transposeAdj.containsKey(top)){
                for(int nbr : transposeAdj.get(top)){
                    indegree[nbr]--;

                    if(indegree[nbr] == 0){
                        q.offer(nbr);
                    }
                }
            }
        }

        if(result.size() > 0){
            Collections.sort(result);           
        }


        return result;       
    }
}