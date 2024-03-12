package DynamicProgramming.PartitionAndMCM;

public class LargestSumofAverages_LC813 {
    

    

    //Time: O(Expenential)
    //Space: O(N){Aux. Stack space}
    private double solveByRecursion(int index,int k,int n,int[] nums){

        //Base Case
        if(k == 0 && index >= n){
            return 0;
        }
        if((k == 0) || (index >= n)){
            return -(double)(1e9);
        }


        double sum = 0;
        double count = 0;
        double maxScore = -(double)(1e9);

        for(int i=index; i<n; i++){

            sum += nums[i];
            count++;

            double score = solveByRecursion(i+1, k-1,n,nums);

            if(score != -(double)(1e9)){
                maxScore = Math.max(maxScore, (sum/count) + score);
            }
        }

        return maxScore;

    }

    //Time: O((N ^ 2) * K)
    //Space: O(N + (N * K)){Aux. Stack space and 2D DP Array}
    private double solveByTopDownDP(int index,int k,int n,int[] nums,double[][] dp){

        //Base Case
        if(k == 0 && index >= n){
            return 0;
        }
        if((k == 0) || (index >= n)){
            return -(double)(1e9);
        }

        //Overlapping sub-problem
        if(dp[index][k] != -1){
            return dp[index][k];
        }


        double sum = 0;
        double count = 0;
        double maxScore = -(double)(1e9);

        for(int i=index; i<n; i++){

            sum += nums[i];
            count++;

            double score = solveByTopDownDP(i+1, k-1,n,nums,dp);

            if(score != -(double)(1e9)){
                maxScore = Math.max(maxScore, (sum/count) + score);
            }
        }

        return dp[index][k] = maxScore;

    }

    //Time: O((N ^ 2) * K)
    //Space: O(N * K){2D DP Array}
    private double solveByBottomUpDP(int k,int n,int[] nums){

        double[][] dp = new double[n+1][k+1];

        //Analyse the base case
        dp[n][0] = 0;

        for(int i=0; i<n; i++){
            dp[i][0] = -(double)(1e9);
        }

        for(int i=1; i<=k; i++){
            dp[n][k] = -(double)(1e9);
        }

        for(int index=n-1; index>=0; index--){
            for(int p=1; p<=k; p++){

                double sum = 0;
                double count = 0;
                double maxScore = -(double)(1e9);

                for(int i=index; i<n; i++){

                    sum += nums[i];
                    count++;

                    double score = dp[i+1][p-1];

                    if(score != -(double)(1e9)){
                        maxScore = Math.max(maxScore, (sum/count) + score);
                    }
                }

                dp[index][p] = maxScore;

            }
        }

        return dp[0][k];

    }

    public double largestSumOfAverages(int[] nums, int k) {


        int n = nums.length;
        
        //return solveByRecursion(0,k,n,nums);

        // double[][] dp = new double[n][k+1];
        // for(double[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        // return solveByTopDownDP(0,k,n,nums,dp);


        return solveByBottomUpDP(k,n,nums);
        
    }
}