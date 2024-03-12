package DynamicProgramming.TwoDimensional;

import java.util.*;


class CountVowelsPermutation_LC1220 {
    private int addMod(int a, int b){
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    //Time: O(5 ^ N){Exponential}
    //Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index,int n,char[] vowels){

        //Base Case
        if(index < 0 || index >= 5){
            return 0;
        }

        if(n == 0 || n == 1){
            return 1;
        }

        int ans = 0;

        if(vowels[index] == 'a'){
            ans = solveByRecursion(index+1,n-1,vowels);
        }else if(vowels[index] == 'e'){
            ans = addMod(solveByRecursion(index-1,n-1,vowels), solveByRecursion(index+1,n-1,vowels));
        }else if(vowels[index] == 'i'){
            ans = addMod( solveByRecursion(index-2,n-1,vowels) ,
                      addMod( solveByRecursion(index-1,n-1,vowels) ,
                      addMod(solveByRecursion(index+1,n-1,vowels), solveByRecursion(index+2,n-1,vowels))));
        }else if(vowels[index] == 'o'){
            ans = addMod(solveByRecursion(index-1,n-1,vowels), solveByRecursion(index+1,n-1,vowels));
        }else{
            ans = solveByRecursion(index-4,n-1,vowels);
        }
        
        return ans;
    }

    //Time: O(5 * N)
    //Space: O(N + (5 * N)){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int index,int n,char[] vowels,int[][] dp){

        //Base Case
        if(index < 0 || index >= 5){
            return 0;
        }

        if(n == 0 || n == 1){
            return 1;
        }

        //Overlapping sub-problem
        if(dp[index][n] != -1){
            return dp[index][n];
        }

        int ans = 0;

        if(vowels[index] == 'a'){
            ans = solveByTopDownDP(index+1,n-1,vowels,dp);
        }else if(vowels[index] == 'e'){
            ans = addMod(solveByTopDownDP(index-1,n-1,vowels,dp), solveByTopDownDP(index+1,n-1,vowels,dp));
        }else if(vowels[index] == 'i'){
            ans = addMod( solveByTopDownDP(index-2,n-1,vowels,dp) ,
                      addMod( solveByTopDownDP(index-1,n-1,vowels,dp) ,
                      addMod(solveByTopDownDP(index+1,n-1,vowels,dp), solveByTopDownDP(index+2,n-1,vowels,dp))));
        }else if(vowels[index] == 'o'){
            ans = addMod(solveByTopDownDP(index-1,n-1,vowels,dp), solveByTopDownDP(index+1,n-1,vowels,dp));
        }else{
            ans = solveByTopDownDP(index-4,n-1,vowels,dp);
        }
        
        return dp[index][n] = ans;
    }

    //Time: O(5 * N)
    //Space: O(5 * N){2D DP Array}
    private int solveByBottomUpDP(int n,char[] vowels){

        int[][] dp = new int[5][n+1];
        //Analyse the base case
        for(int i=0; i<5; i++){

            dp[i][0] = 1;            
            dp[i][1] = 1;
        }       

        for(int curr = 2; curr<=n; curr++){                

            dp[0][curr] = dp[1][curr-1];
            dp[1][curr] = addMod(dp[0][curr-1] , dp[2][curr-1]);
            dp[2][curr] = addMod( dp[0][curr-1] ,
                                addMod( dp[1][curr-1] ,
                                addMod(dp[3][curr-1] , dp[4][curr-1])));
            dp[3][curr] =  addMod(dp[2][curr-1], dp[4][curr-1]);
            dp[4][curr] = dp[0][curr-1];                  
            
        }

        int result = 0;
        for(int i=0; i<5; i++){
            result = addMod(result, dp[i][n]);
        }

        return result;
    }

    public int countVowelPermutation(int n) {
        char[] vowels = new char[]{'a','e','i','o','u'};

        int result = 0;

        // for(int i=0; i<5; i++){
        //     result = addMod(result, solveByRecursion(i,n,vowels));
        // }

        int[][] dp = new int[5][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        for(int i=0; i<5; i++){
            result = addMod(result, solveByTopDownDP(i,n,vowels,dp));
        }        
        return result;

       // return solveByBottomUpDP(n,vowels);
        
    }
}