import java.util.*;
public class PhoneLetterCombination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     String input = "2";
     
     System.out.println(letterCombination(input));
	}
	
	public static List<String> letterCombination(String digits){
		List<String> result = new ArrayList<>();
		StringBuilder output = new StringBuilder();
		
		String mappings[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		
		int index = 0;
		
		solve(digits,output,index,result,mappings);
		return result;
	}
	
	public static void solve(String digits,StringBuilder output,int index,List<String> result,String mappings[]) {
		
		//Base Condition
		if(index >= digits.length()) {
			result.add(new String(output));
			return;
		}
		
	    int digit = (int)(digits.charAt(index) - '0');
	    String value = mappings[digit];
	    for(int i=0;i<value.length();i++) {
	    	output.append(value.charAt(i));
	    	solve(digits,output,index+1,result,mappings);
	    	
	    	//Backtracking
	    	output.deleteCharAt(output.length()-1);
	    }
	    
	}

}
