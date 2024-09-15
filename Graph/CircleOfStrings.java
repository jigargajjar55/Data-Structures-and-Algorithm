public class CircleOfStrings {
    


    
    private void dfs(int node,ArrayList<ArrayList<Integer>> adj,boolean[] visited){
        
        
        visited[node] = true;
        
        for(int nbr : adj.get(node)){
            
            if(!visited[nbr]){
                dfs(nbr,adj,visited);
            }
        }
        
    }
    private int isSCC(int src, ArrayList<ArrayList<Integer>> adj, boolean[] mark){
        
        
        boolean[] visited = new boolean[26];
        
        dfs(src,adj,visited);
        
        for(int i=0; i<26; i++){
            
            if(mark[i] && !visited[i]){
                return 0;
            }
        }
        
        return 1;
        
        
        
    }
    //Time: O(N + E)
    //Space: O(N)
    public int isCircle(String arr[]) {
        
        int n = arr.length;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[26];
        int[] outDegree = new int[26];
        boolean[] mark = new boolean[26];
        
        for(int i=0; i<26; i++){
            adj.add(new ArrayList<>());
        }
        
        
        for(int i=0; i<n; i++){
            
            int firstCh = (int)(arr[i].charAt(0) - 'a');
            int lastCh = (int)(arr[i].charAt(arr[i].length() - 1) - 'a');
            
            mark[firstCh] = true;
            mark[lastCh] = true;
            
            inDegree[lastCh] += 1;
            outDegree[firstCh] += 1;
            
            adj.get(firstCh).add(lastCh);
            
        }
        
        for(int i=0; i<26; i++){
            
            if(inDegree[i] != outDegree[i]){
                return 0;
            }
        }
        
        return isSCC(arr[0].charAt(0) - 'a', adj,mark);
    }
}
