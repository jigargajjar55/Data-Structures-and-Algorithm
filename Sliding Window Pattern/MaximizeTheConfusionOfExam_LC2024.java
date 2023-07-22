public class MaximizeTheConfusionOfExam_LC2024 {
    
    //Time: O((2 * N) + (2 * N)), Space: O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {

        int n = answerKey.length();
        int left = 0;
        int falseCount = 0;
        int ans = 0;

        //We will try to maximize Confusion by Increasing True's 
        for(int right=0; right<n; right++){
            char currCh = answerKey.charAt(right);

            if(currCh == 'F'){
                falseCount++;
            }

            while(left <= right && falseCount > k){
                char ch = answerKey.charAt(left);
                if(ch == 'F'){
                    falseCount--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        left = 0;
        int trueCount = 0;

        //We will try to maximize Confusion by Increasing False's 
        for(int right=0; right<n; right++){
            char currCh = answerKey.charAt(right);

            if(currCh == 'T'){
                trueCount++;
            }

            while(left <= right && trueCount > k){
                char ch = answerKey.charAt(left);
                if(ch == 'T'){
                    trueCount--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        //We will get ans by getting maximum of both options
        return ans;

    }
}