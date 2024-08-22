import java.util.*;

public class BuildMatrixWithConditions_LC2392 {
    

    
    private boolean isCycle(int node,boolean[] visited,boolean[] pathVisited, Map<Integer, List<Integer>> adj,Stack<Integer> order){

        visited[node] = true;
        pathVisited[node] = true;

        if(adj.containsKey(node)){

            for(int nbr : adj.get(node)){

                if(!visited[nbr]){
                    if(isCycle(nbr,visited,pathVisited,adj,order)){
                        return true;
                    }
                }else if(pathVisited[nbr]){
                    return true;
                }
            }
        }

        pathVisited[node] = false;

        order.push(node);

        return false;

    }
    private boolean getTopoSort(int k, int[][] edges, Stack<Integer> order){
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int size = edges.length;

        for(int i=0; i<size; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            if(!adj.containsKey(u)){
                adj.put(u, new ArrayList<>());
            }
            adj.get(u).add(v);
        }

        boolean[] visited = new boolean[k+1];
        boolean[] pathVisited = new boolean[k+1];

        for(int node=1; node<=k; node++){

            if(!visited[node]){
                if(isCycle(node,visited,pathVisited, adj, order)){
                    return true;
                }
            }
        }

        return false;
    }
    //Time: O(K ^ 2)
    //Space: O(K ^ 2)
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        
        Stack<Integer> rowTopoSort = new Stack<>();
        Stack<Integer> colTopoSort = new Stack<>();

        boolean cyclePresentInRow = getTopoSort(k,rowConditions,rowTopoSort);
        if(cyclePresentInRow){
            return new int[0][0];
        }
        boolean cyclePresentInCol = getTopoSort(k,colConditions ,colTopoSort);
        if(cyclePresentInCol){
            return new int[0][0];
        }

        Map<Integer, Integer> value_to_Row = new HashMap<>();
        Map<Integer, Integer> value_to_Col = new HashMap<>();

        int index = 0;
        while(!rowTopoSort.isEmpty()){
            int top = rowTopoSort.pop();
            value_to_Row.put(top, index);
            index++;
        }

        index = 0;
        while(!colTopoSort.isEmpty()){
            int top = colTopoSort.pop();
            value_to_Col.put(top, index);
            index++;
        }

        int[][] result = new int[k][k];

        for(int node=1; node<=k; node++){

            int rowIndex = value_to_Row.get(node);
            int colIndex = value_to_Col.get(node);
            result[rowIndex][colIndex] = node;
        }




        return result;

        
    }
}