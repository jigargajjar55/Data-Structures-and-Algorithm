package Stack;

public class ValidParenthesisString_LC678 {

    /*
     * 
     * 
     * For this Approach, iterate the character Array two times from Left to Right
     * and from Right to Left.
     * We in both times have to count the number of stars coming using countStar.
     * For Left to Right iteration have to see how much Left parenthesis are closed
     * by the Right parenthesis by incrementing and decrementing the countLeft
     * variable.
     * If there is alone right parenthesis comes then have to check that is there
     * any star came before , if so then have to treat that star as left parenthesis
     * and have to close that parenthesis pair.
     * And same as in Right to Left iteration have to perform same operation but for
     * checking that if the left parenthesis is alone then can treat any star at the
     * right of it as right paranthesis or not.
     */
    //Time: O(2 * N)
    //Space: O(1)
    public boolean checkValidStringTwoPass(String s) {

        int n = s.length();

        int open = 0;
        int close = 0;
        int starCount = 0;

        //Checking for valid parenthesis from left to right
        for (int i = 0; i < n; i++) {

            char currCh = s.charAt(i);

            if (currCh == '(') {
                open++;
            } else if (currCh == ')') {

                if (open == 0) {
                    if (starCount == 0) {
                        return false;
                    } else {
                        starCount--;
                    }

                } else {
                    open--;
                }

            } else {
                starCount++;
            }
        }

        //Checking for valid parenthesis from right to left
        starCount = 0;

        for (int i = n - 1; i >= 0; i--) {

            char currCh = s.charAt(i);

            if (currCh == ')') {
                close++;
            } else if (currCh == '(') {

                if (close == 0) {
                    if (starCount == 0) {
                        return false;
                    } else {
                        starCount--;
                    }

                } else {
                    close--;
                }

            } else {
                starCount++;
            }
        }

        return true;

    }

    // We have optimised the code of two pass into one loop
    //Time: O(N)
    //Space: O(1)
    public boolean checkValidStringOnePass(String s) {

        int len = s.length();
        int n = len - 1;
        int open = 0;
        int close = 0;      
        
        for(int i=0; i<len; i++){

            //Char from left to right
            char leftCh = s.charAt(i);
            //Char from right to left
            char rightCh = s.charAt(n - i);

            if(leftCh == '(' || leftCh == '*'){
                open++;
            }else{
                open--;
            }

            if(rightCh == ')' || rightCh == '*'){
                close++;
            }else{
                close--;
            }

            if(open < 0 || close < 0){
                return false;
            }            
        }

        return true;

    }


}
