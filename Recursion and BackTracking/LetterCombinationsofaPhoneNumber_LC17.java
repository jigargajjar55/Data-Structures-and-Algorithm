import java.util.*;

public class LetterCombinationsofaPhoneNumber_LC17 {

    
    //Time: O((D) ^ (M)), Space: O(D + D) {Aux. Stack Space and extra StringBuilder for constructing output} [D : length of digits, M: length of mapped String]
    private void  dfsSolve(int index,int n,String digits,StringBuilder output,List<String> result,String[] mapping){

        //Base Case
        if(index == n){
            result.add(output.toString());
            return;
        }

        int currNumber = (int)(digits.charAt(index) - '0');
        String currValue = mapping[currNumber];

        for(int i=0; i<currValue.length(); i++){
            output.append(currValue.charAt(i));

            dfsSolve(index+1,n,digits,output,result,mapping);

            output.deleteCharAt(output.length() - 1);
        }

    }
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        int n = digits.length();
        if(n == 0){
            return result;
        }
        String[] mapping = {"","", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        StringBuilder output = new StringBuilder();

        dfsSolve(0,n,digits,output,result,mapping);

        return result;
        
    }
}