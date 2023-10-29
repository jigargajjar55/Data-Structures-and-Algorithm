package Stack;

public class BackspaceStringCompare_LC844 {

    // Time: O(M + N)
    // Space: O(M + N)
    public boolean backspaceCompare1(String s, String t) {
        int m = s.length();
        int n = t.length();

        StringBuilder s1 = new StringBuilder();
        StringBuilder t1 = new StringBuilder();

        for (int i = 0; i < m; i++) {
            char currCh = s.charAt(i);
            int size = s1.length();

            if (currCh == '#') {
                if (size > 0) {
                    s1.deleteCharAt(size - 1);
                }

            } else {
                s1.append(currCh);
            }
        }
        for (int i = 0; i < n; i++) {
            char currCh = t.charAt(i);
            int size = t1.length();

            if (currCh == '#') {
                if (size > 0) {
                    t1.deleteCharAt(size - 1);
                }

            } else {
                t1.append(currCh);
            }
        }

        if (s1.length() == t1.length()) {

            for (int i = 0; i < s1.length(); i++) {

                if (s1.charAt(i) != t1.charAt(i)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }



    // NeetoCode Video Link:  https://www.youtube.com/watch?v=k2qrymM_DOo

    //Two Pointers Approach
    //Time: O(M + N)
    //Space: O(1)
    private int nextValidCharIndex(String str, int index){

        int backSpace = 0;

        while(index >= 0){

            if(backSpace == 0 && str.charAt(index) != '#'){
                break;
            }else if(str.charAt(index) == '#'){
                backSpace++;
            }else{
                backSpace--;
            }
            index--;
        }
        return index;
    }

    public boolean backspaceCompare(String s, String t) {

        int ptr1 = s.length()-1;
        int ptr2 = t.length()-1;

        while(ptr1 >= 0 || ptr2 >= 0){

            ptr1 = nextValidCharIndex(s,ptr1);
            ptr2 = nextValidCharIndex(t,ptr2);

            char nextCharS = (ptr1 >= 0) ? s.charAt(ptr1) : '@';
            char nextCharT = (ptr2 >= 0) ? t.charAt(ptr2) : '@';

            if(nextCharS != nextCharT){
                return false;
            }
            ptr1--;
            ptr2--;
        }

        return true;
        
    }
}