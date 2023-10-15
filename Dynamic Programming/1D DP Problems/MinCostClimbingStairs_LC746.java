public class MinCostClimbingStairs_LC746 {

    
    //Time: O(2 ^ N),
    //Space: O(N){Aux. Stack space}
    private int solveByRecursion(int step,int n,int[] cost){
        //Base Case
        if(step == n){
            return 0;
        }
        if(step > n){
            return (int)(1e9);
        }        

        int oneStep = solveByRecursion(step+1,n,cost);
        int twoStep = solveByRecursion(step+2,n,cost);

        int ans = cost[step] + Math.min(oneStep, twoStep);

        return ans;

    }

    //Time: O(N),
    //Space: O(N + N){Aux. Stack space and 1D DP Array}
    private int solveByTopDownDP(int step,int n,int[] cost,int[] dp){
        //Base Case
        if(step == n){
            return 0;
        }
        if(step > n){
            return (int)(1e9);
        }   

        //Overlapping sub-problem
        if(dp[step] != -1){
            return dp[step];
        }     

        int oneStep = solveByTopDownDP(step+1,n,cost,dp);
        int twoStep = solveByTopDownDP(step+2,n,cost,dp);

        int ans = cost[step] + Math.min(oneStep, twoStep);

        return dp[step] = ans;
    }

    //Time: O(N),
    //Space: O(N){1D DP Array}
    private int solveByBottomUpDP(int n,int[] cost){
        int[] dp = new int[n+1];
        //Analyse the base case
        dp[n] = 0;

        for(int step=n-1; step>=0; step--){

            int oneStep = dp[step + 1];
            int twoStep = (int)(1e9);
            
            if(step+2 <= n){
                twoStep = dp[step + 2];
            }
           
            int ans = cost[step] + Math.min(oneStep, twoStep);
            dp[step] = ans;
        }

        return Math.min(dp[0], dp[1]);
    }

    //Time: O(N),
    //Space: O(1)
    private int solveBySpaceOptimise(int n,int[] cost){
        
        int prev2 = (int)(1e9);
        int prev1 = 0;

        for(int step=n-1; step>=0; step--){

            int oneStep = prev1;
            int twoStep = prev2;
            
           
            int ans = cost[step] + Math.min(oneStep, twoStep);
            
            prev2 = prev1;
            prev1 = ans;
        }

        return Math.min(prev2, prev1);
    }

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;

        //return Math.min(solveByRecursion(0,n,cost),solveByRecursion(1,n,cost));

        // int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);
        // return Math.min(solveByTopDownDP(0,n,cost,dp),solveByTopDownDP(1,n,cost,dp));

        //return solveByBottomUpDP(n,cost);

        return solveBySpaceOptimise(n,cost);
        
    }
}