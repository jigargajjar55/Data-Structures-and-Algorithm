package Maths;
import java.util.*;
public class powerofB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Scanner scan = new Scanner(System.in);
      
      int a = scan.nextInt();
      int b = scan.nextInt();
      
      int ans = powerOf(a,b);
      System.out.println(ans);
	}
	
	public static int powerOf(int a,int b) {
		//Base condition
		if(b==0) {
			return 1;
		}
		if(b==1) {
			return a;
		}
		
		int ans = powerOf(a,b/2);
		
		if((b & 1) == 1) {
			return a * ans * ans;
		}else {
			return ans * ans;
		}
	}

}
