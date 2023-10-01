import java.util.*;

public class ShortestPathVisitingAllNodes_LC847 {

    
    /*
     * 
    https://leetcode.com/problems/shortest-path-visiting-all-nodes/solutions/549233/breadth-first-search-bfs-with-intuitive-approach-thinking-process-13-ms/?envType=daily-question&envId=2023-09-17 
     * 
     * 
     * Time: O(N * (2 ^ N)), where N is the number of nodes. This is because there
             are 2^n possible subsets of nodes and n nodes to consider for each subset.
     * Space: O(N * (2 ^ N)), needed for the visited array and the queue.
     */


    private class Pair{
        int mask;
        int lead;
        Pair(int mask,int lead){
            this.mask = mask;
            this.lead = lead;
        }
    }
    private int setBit(int mask,int lead){
        return (mask | (1 << lead));
    }
    public int shortestPathLength(int[][] graph) {

        int nodes = graph.length;
        
        Queue<Pair> q = new LinkedList<>();
        int rows = (int)(Math.pow(2,nodes));
        int cols = nodes;
        boolean[][] visited = new boolean[rows][cols];

        //In-order to start BFS simultaneously for all node as source node
        for(int node=0; node<nodes; node++){
            int lead = node;
            int mask = setBit(0, lead);

            q.offer(new Pair(mask,lead));
        }

        int minPath = 0;

        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0; i<size; i++){
                Pair top = q.peek();
                q.poll();

                int currLead = top.lead;
                int currMask = top.mask;

                if(currMask == rows - 1){
                    return minPath;
                }

                for(int nbr : graph[currLead]){

                    int nbrLead = nbr;
                    int nbrMask = setBit(currMask, nbrLead);

                    if(!visited[nbrMask][nbrLead]){
                        q.offer(new Pair(nbrMask, nbrLead));
                        visited[nbrMask][nbrLead] = true;
                    }
                }
            }

            minPath++;
        }

        return -1;        
        
    }
}