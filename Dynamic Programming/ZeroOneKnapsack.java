import java.util.* ;
import java.io.*; 

public class Solution{

    static int solveByRecursion(int[] weight, int[] value, int index, int capacity) {

        //Base condition
        if(index == 0){
            if(weight[index] <= capacity){
                return value[index];
            }else{
                return 0;
            }
        }

        int include = 0;

        if(weight[index] <= capacity){
            include = value[index] + solveByRecursion(weight,value,index-1,capacity-weight[index]);

        }

        int exclude = solveByRecursion(weight,value,index-1,capacity);

        int ans = Math.max(include,exclude);

        return ans;


    }

     static int solveByMem(int[] weight, int[] value, int index, int capacity,int[][] dp) {

        //Base condition
        if(index == 0){
            if(weight[index] <= capacity){
                return value[index];
            }else{
                return 0;
            }
        }

        if(dp[index][capacity] != -1){
            return dp[index][capacity];
        }

        int include = 0;

        if(weight[index] <= capacity){
            include = value[index] + solveByMem(weight,value,index-1,capacity-weight[index],dp);

        }

        int exclude = solveByMem(weight,value,index-1,capacity,dp);

        dp[index][capacity] = Math.max(include,exclude);

        return dp[index][capacity];

    }

     static int solveByTab(int[] weight, int[] value, int n, int capacity) {

        //Step1 : Create a DP Array
        int[][] dp = new int[n][capacity+1];

        //Step2 : Analyse Base case and add it in DP Array
        for(int w=weight[0]; w<=capacity; w++){
            if(weight[0] <= capacity){
                dp[0][w] = value[0];
            }else{
                dp[0][w] = 0;
            }
        }

        for(int index=1;index<n; index++){
            for(int w=0; w<=capacity; w++){

                int include = 0;

                if(weight[index] <= w){
                    include = value[index] + dp[index-1][w-weight[index]];

                }

                int exclude = dp[index-1][w];

                dp[index][w] = Math.max(include,exclude);

            }
        }
        return dp[n-1][capacity];

    }


     static int solveByTabSpaceOptmize(int[] weight, int[] value, int n, int capacity) {

        //Step1 : Create a DP Array
       // int[][] dp = new int[n][capacity+1];
         
      
        int[] curr = new int[capacity+1];

        //Step2 : Analyse Base case and add it in DP Array
        for(int w=weight[0]; w<=capacity; w++){
            if(weight[0] <= capacity){
                curr[w] = value[0];
            }else{
                curr[w] = 0;
            }
        }

        for(int index=1;index<n; index++){
            for(int w=capacity; w>=0; w--){

                int include = 0;

                if(weight[index] <= w){
                    include = value[index] + curr[w-weight[index]];

                }

                int exclude = curr[w];

                curr[w] = Math.max(include,exclude);

            }
        }
        return curr[capacity];

    }

    
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        //return solveByRecursion(weight,value,n-1,maxWeight);
        
        // int[][] dp = new int[n][maxWeight+1];
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<=maxWeight; j++){
        //         dp[i][j] = -1;
        //     }
        // }

        // return solveByMem(weight,value,n-1,maxWeight,dp);
           

        //   return solveByTab(weight,value,n,maxWeight);

           return solveByTabSpaceOptmize(weight,value,n,maxWeight);

    }
}