import java.util.*;

public class factorialRecursive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter a number: ");
       int n = scan.nextInt();
       
//       int ans = factorial(n);       
//       System.out.println("Factorial of "+n+" : "+ ans);      
      // int ans = powerOfTwo(n);
       //System.out.println(n + " Power of 2 : "+ ans);
       
       printCountDown(n);
	}
	
	public static int factorial(int num) {
		
		if(num == 0) {
			return 1;
		}
		
		return num * (factorial(num-1));
	}

	public static int powerOfTwo(int num) {
		
		if(num == 0) {
			return 1;
		}
		
		return 2 * (powerOfTwo(num-1));
	}

	public static void printCountDown(int num) {
		
		if(num == 0) {
			return;
		}
		
		printCountDown(num-1);
		
		System.out.println(num);
		
	}
	


}
