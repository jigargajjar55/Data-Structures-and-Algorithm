package Simulation;

public class FindTheWinnerofanArrayGame_LC1535 {
    

    // https://leetcode.com/problems/find-the-winner-of-an-array-game/solutions/4250046/for-beginners-ii-beats-100-ii-just-for-loop-if-condition/?envType=daily-question&envId=2023-11-05

    //Time: O(N),
    //Space: O(1)
    
    public int getWinner(int[] arr, int k) {

        int n = arr.length;

        int winner = arr[0];
        int winStrike = 0;
       

        for(int i=1; i<n; i++){

            if(winner < arr[i]){

                winner = arr[i];
                winStrike = 0;
            }

            winStrike += 1;             

            if(winStrike == k){
                break;
            }
        } 

        return winner;
        
    }
}