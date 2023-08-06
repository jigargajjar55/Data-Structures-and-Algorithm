import java.util.*;

public class UniqueBinarySearchTrees_LC96 {
 
    
    /*


        This is just a helpful hint to think about the problem, it does not give away the solution, 
        but might help guide someone who is looking for it in the right direction.

        Suppose I want to know how many distinct BST there are with n nodes from 0 to n-1.

        If my BST has n-1 at the head, how many nodes will be on the left side? How many nodes will be on
        the right side?

        If my BST has n-2 at the head, how many nodes will be on the left side? How many nodes will be on
        the right side?



    */
    //Time: O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int n){

        //Base Case
        if(n <= 1){
            return 1;
        }

        int bst = 0;
        for(int i=0; i<n; i++){
            bst += solveByRecursion(i) * solveByRecursion(n-i-1);
        }

        return bst;
    }

    //Time: O(N * N), Space: O(N + N){Aux. Stack Space and DP Array}
    private int solveByTopDownDP(int n,int[] dp){

        //Base Case
        if(n <= 1){
            return 1;
        }

        //Overlapping sub-problem
        if(dp[n] != -1){
            return dp[n];
        }

        int bst = 0;
        for(int i=0; i<n; i++){
            bst += solveByTopDownDP(i,dp) * solveByTopDownDP(n-i-1,dp);
        }

        return dp[n] = bst;

    }
    public int numTrees(int n) {

        //return solveByRecursion(n);

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        return solveByTopDownDP(n,dp);       
        
    }
}
