import java.util.*;
public class NinjaTraining {
        
    //Time : Exponetial Complexity(~ 2 ^ (N * 4)), Space :  O(N){For Recursive call stack space}
    private static int solveByRecursion(int day,int lastTask, int points[][]){

        //Base case
        if(day == 0){
            int maxi = 0;

            for(int task=0; task<3; task++){
                if(task != lastTask){
                    maxi = Math.max(maxi,points[day][task]);
                }               
            }
            return maxi;
        }

        int maxi = 0;

        for(int task=0; task<3; task++){
            if(task != lastTask){
                int point = points[day][task] + solveByRecursion(day-1,task,points);
                maxi = Math.max(maxi,point);
            }
        }
        return maxi;
    }

    //Time : O(N * (4 * 3)), Space : O(N * 4)+ O(N){For DP Appray and Recursive call stack space}
    private static int solveByTopDownDP(int day,int lastTask, int points[][],int[][] dp){


        if(dp[day][lastTask] != -1){
            return dp[day][lastTask];
        }
        //Base case
        if(day == 0){
            int maxi = 0;

            for(int task=0; task<=2; task++){
                if(task != lastTask){
                    maxi = Math.max(maxi,points[0][task]);
                }               
            }
            return dp[day][lastTask] = maxi;
        }        

        int maxi = 0;

        for(int task=0; task<=2; task++){
            if(task != lastTask){
                int point = points[day][task] + solveByTopDownDP(day-1,task,points,dp);
                maxi = Math.max(maxi,point);
            }
        }
        return dp[day][lastTask] = maxi;
    }


    //Time : O(N * (4 * 3)), Space : O(N * 4){For DP Appray}
    private static int solveByBottomUpDP(int days,int tasks, int points[][]){


        int[][] dp = new int[days][tasks];
        //Base case
        for(int i=0; i<4; i++){
            int maxi = 0;
            for(int task=0; task<=2; task++){
                if(task != i){
                    maxi = Math.max(maxi,points[0][task]);
                }
            }
            dp[0][i] = maxi;
        }                  

        for(int day = 1;day<days; day++){

            for(int i=0; i<4; i++){

                int maxi = 0;

                for(int task=0; task<=2; task++){
                    if(task != i){
                        int point = points[day][task] + dp[day-1][task];
                        maxi = Math.max(maxi,point);
                    }
                }
                dp[day][i] = maxi;
            }
        }       

        return dp[days-1][tasks-1];
    }



    //Time : O(N * (4 * 3)), Space : O(4){For 1D DP Appray}
    private static int solveBySpaceOptimization(int days, int points[][]){


        int[] prev = new int[4];
        //Base case
        for(int i=0; i<4; i++){
            int maxi = 0;
            for(int task=0; task<=2; task++){
                if(task != i){
                    maxi = Math.max(maxi,points[0][task]);
                }
            }
            prev[i] = maxi;
        }
                  

        for(int day = 1;day<days; day++){

            int[] curr = new int[4];

            for(int i=0; i<4; i++){

                int maxi = 0;

                for(int task=0; task<=2; task++){
                    if(task != i){
                        int point = points[day][task] + prev[task];
                        maxi = Math.max(maxi,point);
                    }
                }

                curr[i] = maxi;

            }
            prev = curr;
        }
        return prev[3];
    }

    public static int ninjaTraining(int n, int points[][]) {

        //return solveByRecursion(n-1,3,points);

        // int dp[][] = new int[n][4];
        // for (int[] row: dp){
        //     Arrays.fill(row, -1);
        // }           

        // return solveByTopDownDP(n-1,3,points,dp);

       // return solveByBottomUpDP(n,4,points);

        return solveBySpaceOptimization(n,points);

    }

}