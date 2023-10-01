package Strings;

public class FindTheDifference_LC389 {
    

    //Time: O(S + T + 26)
    //Space: O(2 * 26)
    public char findTheDifference(String s, String t) {

        int sLength = s.length();
        int tLength = t.length();
        int[] charCountS = new int[26];
        int[] charCountT = new int[26];

        for(int i=0; i<sLength; i++){
            int index = (int)(s.charAt(i) -'a');
            charCountS[index]++; 
        }

        for(int i=0; i<tLength; i++){
            int index = (int)(t.charAt(i) -'a');
            charCountT[index]++; 
        }

        int resultIndex = 0;

        for(int i=0; i<26; i++){
            if(charCountS[i] != charCountT[i]){
                resultIndex = i;
                break;
            }
        }

        char resultChar = (char)(resultIndex + 'a');
        return resultChar;
        
    }
}