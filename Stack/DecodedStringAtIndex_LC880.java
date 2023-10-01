package Stack;

public class DecodedStringAtIndex_LC880 {
    

    

    // https://www.youtube.com/watch?v=6iE7QJRMXzs
    //Time: O(N)
    //Space: O(1)

    public String decodeAtIndex(String s, int k) {

        long len = 0;
        int n = s.length();
        for(char ch: s.toCharArray()) {
            if(Character.isDigit(ch)) {
                len *= ch - '0';
            } else {
                len++;
            }
        }
        
        for(int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            k %= len;
            if((k == 0 || k == len) && Character.isLetter(ch)) {
                return Character.toString(ch);
            } else if(Character.isDigit(ch)){
                len /= (ch - '0');
            } else {
                len--;
            }
        }
        
        return "";
    }
}