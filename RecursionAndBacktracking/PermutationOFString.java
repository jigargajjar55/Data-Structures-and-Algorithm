package RecursionAndBacktracking;
import java.util.*;
public class PermutationOFString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String str = "dabc";
       
       System.out.println(generatePermutation(str));
	}
	
	public static ArrayList<String> generatePermutation(String str){
		
		ArrayList<String> ans = new ArrayList<>();
		StringBuilder output = new StringBuilder(str);
		
		int index = 0;
		solve(output,index,ans);
		Collections.sort(ans);
		return ans;		
	}
	
	public static void solve(StringBuilder output,int index,ArrayList<String> ans) {
		
		//Base Condition
		if(index >= output.length()) {
			ans.add(new String(output));
			return;
		}
		
		for(int j=index;j<output.length();j++) {
			
			//Swap character
			char ch = output.charAt(j);
			output.setCharAt(j, output.charAt(index));
			output.setCharAt(index, ch);
			
			solve(output,index+1,ans);
			
			//Backtracking
			ch = output.charAt(j);
			output.setCharAt(j, output.charAt(index));
			output.setCharAt(index, ch);
			
			
		}
		
	}

}
