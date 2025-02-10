package Tree.BinaryTree;

public class KSumPaths {

    //Time: (N ^ 2)
    //Space: O(Height of Tree)
    //Bruteforce approach
    private int dfs(Node root, int k, List<Integer> path){
        
        
        //Base Case
        if(root == null){
            return 0;
        }
        
        
        path.add(root.data);
        
        int ans = dfs(root.left, k, path);
        ans += dfs(root.right, k, path);
        
        int size = path.size();
        int pathSum = 0;
        
        for(int i=size-1; i>=0; i--){
            
            pathSum += path.get(i);
            
            if(pathSum == k){
                ans += 1;
                
            }
        }
        
        //Back-tracking
        path.remove(size-1);
        
        return ans;
        
        
    }
    
    //Time: (N)
    //Space: O(Height of Tree)
    //Optimise approach    
    private int prefixDFS(Node root, int currSum, int k, Map<Integer, Integer> prefixMap){
        
        
        //Base Case
        if(root == null){
            return 0;
        }
        
        
        currSum += root.data;
        int count = 0;
        
        // Pathsum from root to current node is equal to k
        if(currSum == k){
            count++;
        }
        
        // The count of curr_sum − k gives the number of paths 
        // with sum k up to the current node
        count += prefixMap.getOrDefault(currSum - k, 0);
        
        int orgValue = prefixMap.getOrDefault(currSum, 0);
        
        prefixMap.put(currSum, orgValue + 1);
        
        count += prefixDFS(root.left,currSum,k,prefixMap);
        count += prefixDFS(root.right,currSum,k,prefixMap);
        
        //Back-tracking
        prefixMap.put(currSum, orgValue);
        
        return count;
        
        
        
    }
    
    
    
    public int sumK(Node root, int k) {
        
        
        //List<Integer> path = new ArrayList<>();
        //int ans = dfs(root,k,path);
        
        
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int ans = prefixDFS(root,0,k,prefixMap);
        
        return ans;
        
        
    }
    
}
