package Maths;
class LastMomentBeforeAllAntsFallOutOfPlank_LC1503 {

    // https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/solutions/4246129/more-than-one-way-detailed-explanations-java-c-python-javascript-c/?envType=daily-question&envId=2023-11-04



    //Time: O(N)
    //Space: O(1)
    public int getLastMoment(int n, int[] left, int[] right) {

        int l = left.length;
        int r = right.length;

        int ans = 0;

        // Iterate through the positions of ants moving to the left
        for(int i=0; i<l; i++){
            // Update the maximum time if the current left-moving ant's position is greater
            // than the previously recorded maximum time

            int currPos = left[i];
            int time = currPos;

            if(ans < time){
                ans = time;
            }
        }

        // Iterate through the positions of ants moving to the right
        for(int i=0; i<r; i++){
            // Update the maximum time if the current right-moving ant's position (relative to
            // the right end of the plank) is greater than the previously recorded maximum time
            int currPos = right[i];
            int time = n - currPos;

            if(ans < time){
                ans = time;
            }
        }

        // The final 'time' variable contains the maximum time, which is when the last ant(s)
        // fall off the plank, so return it as the result.
        return ans;
        
    }
}