import java.util.*;

class BagofTokens_LC948 {
    //Time: O(N * log N)
    //Space: O(1)
    public int bagOfTokensScore(int[] tokens, int power) {
        
        //Sort an array
        Arrays.sort(tokens);

        int n = tokens.length;

        int score = 0;
        int maxScore = 0;
        int left = 0;
        int right = n - 1;

        while(left <= right){
            
            if(power >= tokens[left]){
                score += 1;
                maxScore = Math.max(maxScore, score);
                power -= tokens[left];
                left++;
            }else if(score > 0){
                score -= 1;
                power += tokens[right];
                right--;
            }else{
                break;
            }
        }

        return maxScore;
    }
}