import java.util.*;
public class FastExponentiationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter a number a:");
    int a = scan.nextInt();
    System.out.println("Enter a number b:");
    int b = scan.nextInt();
    
    int ans = power(a,b);
    System.out.println("Result is: "+ ans);
	}
	
	public static int power(int a,int b) {
		
		
		//Base condition
		if(b == 0) {
			return 1;
		}
		if(b == 1) {
			return a;
		}
		
		int ans = power(a,b>>1);
		
		if((b & 1) == 1) {
			return a*ans*ans;
		}else {
			return ans*ans;
		}
		
	}

}
