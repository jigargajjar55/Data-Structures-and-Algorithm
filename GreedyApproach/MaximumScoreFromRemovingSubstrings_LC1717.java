package GreedyApproach;

public class MaximumScoreFromRemovingSubstrings_LC1717 {

    
    private String removeSubstr(String s,String targetStr){

        Stack<Character> st = new Stack<>();
        int n = s.length();

        for(int i=0; i<n; i++){

            char ch = s.charAt(i);

            if(ch == targetStr.charAt(1) && !st.isEmpty() && st.peek() == targetStr.charAt(0)){
                st.pop();
            }else{
                st.push(ch);
            }
        }

        StringBuilder result = new StringBuilder();
        while(!st.isEmpty()){
            result.append(st.pop());
        }
        return result.reverse().toString();

    }
    //Time: O(2 * N)
    //Space: O(N)
    //Greedy approach
    public int maximumGain(String s, int x, int y) {

        int result = 0;
        int n = s.length();

        String highPriorityStr = (x > y) ? "ab" : "ba";
        String lowPriorityStr = highPriorityStr.equals("ab") ? "ba" : "ab";

        String remainingStr = removeSubstr(s,highPriorityStr);


        int removedPair = ((n - remainingStr.length()) / 2);

        result += (removedPair * Math.max(x,y));

        String finalStr = removeSubstr(remainingStr,lowPriorityStr);

        removedPair = ((remainingStr.length() - finalStr.length()) / 2);

        result += (removedPair * Math.min(x,y));

        return result;        
    }
}
