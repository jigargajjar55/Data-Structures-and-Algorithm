import java.util.*;

public class MinCostToConnectAllPoints_LC1584 {   

    
    private class Pair{
        int node;
        double weight;
        Pair(int node,double weight){
            this.node = node;
            this.weight = weight;
        }
    }
    
    private class Tuple{
        int node;
        int parent;
        double weight;
        Tuple(int node,int parent,double weight){
            this.node = node;
            this.parent = parent;
            this.weight = weight;
        }
    }
    
    //Time: O( (N ^ 2) + (N * log N){For Prim's Algo})
    //Space: O(2N + 2E){For mst array,Adjeceny list and PriorityQueue}
    public int minCostConnectPoints1(int[][] points) {

        int n = points.length;
        Map<Integer, List<Pair>> adj = new HashMap<>();

        PriorityQueue<Tuple> pq = new PriorityQueue<>((p1,p2) -> Double.compare(p1.weight,p2.weight));

        for(int i=0; i<n; i++){
            adj.put(i, new ArrayList<>());

            for(int j=0; j<n; j++){

                if(i != j){
                    double manhattenDist = (double)(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]));
                    adj.get(i).add(new Pair(j,manhattenDist));                  

                }
            }            
        }

        double minCost = 0;
        boolean[] mst = new boolean[n];        
        pq.offer(new Tuple(0,-1,0));

        while(!pq.isEmpty()){
            
            Tuple top = pq.peek();
            pq.poll();

            int node = top.node;
            int parent = top.parent;
            double weight = top.weight;

            if(mst[node]){
                continue;
            }

            if(parent != -1){
                minCost += weight;
            }

            mst[node] = true;

            if(adj.containsKey(node)){

                for(Pair nbr : adj.get(node)){
                    int nbrNode = nbr.node;
                    double nbrWeight = nbr.weight;

                    if(!mst[nbrNode]){
                        pq.offer(new Tuple(nbrNode,node,nbrWeight));
                    }                   
                }
            }
        }

        return (int)minCost;        
    }

    //Time: O(N * log N){For Prim's Algo}
    //Space: O(2N + 2E){For Adjeceny list and PriorityQueue}
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
            return a[0] - b[0];
        });
        boolean[] mst = new boolean[n];
        
        pq.add(new int[]{0, 0});
        
        int wt = 0;
        int totalNodes = 0;
        
        while(totalNodes < n) {
            int[] rm = pq.remove();
            
            if(mst[rm[1]]) {
                continue;
            }
            
            mst[rm[1]] = true;
            wt += rm[0];
            totalNodes++;
            
            for(int i = 0; i < n; i++) {
                if(!mst[i]) {
                    pq.add(new int[]{Math.abs(points[rm[1]][0] - points[i][0]) + 
                                     Math.abs(points[rm[1]][1] - points[i][1]), i});
                }
            }
            
        }
        
        return wt;
    }
}