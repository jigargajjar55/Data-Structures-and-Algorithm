import java.util.*;

public class CourseSchedule_LC207 {   

    
    private boolean dfs(int node, Map<Integer, List<Integer>> adj,boolean[] visited,boolean[] pathVisited,boolean[] finishedCourses){

        visited[node] = true;
        pathVisited[node] = true;

        if(adj.containsKey(node)){
            for(int nbr : adj.get(node)){
                if(!visited[nbr]){
                    boolean ans = dfs(nbr,adj,visited,pathVisited, finishedCourses);
                    if(ans){
                        return true;
                    }
                }else if(pathVisited[nbr]){
                    return true;
                }
            }
        }

        finishedCourses[node] = true;

        //Backtracking
        pathVisited[node] = false;
        return false;

    }
    //Time: O(N + E), Space: O(3 * N + (N + E))
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] pathVisited = new boolean[numCourses];
        boolean[] finishedCourses = new boolean[numCourses];
        int n = prerequisites.length;

        for(int i=0; i<n; i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];

            if(!adj.containsKey(v)){
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(u);
        }   
        
        for(int i=0; i<numCourses; i++){
            if(!visited[i]){
                dfs(i, adj,visited,pathVisited, finishedCourses);
            }
        }

        boolean ans = true;
        for(int i=0; i<numCourses; i++){
            if(!finishedCourses[i]){
                ans = false;
                break; 
            }
        }

        return ans;
    }
}