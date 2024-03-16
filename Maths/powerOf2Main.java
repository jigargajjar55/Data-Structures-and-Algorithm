package Maths;
import java.util.Scanner;

public class powerOf2Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number: ");
		int number = scanner.nextInt();	

		
		System.out.println(isPowerofTwo(number));
		
	}
	
	static boolean isPowerofTwo(int n) {
		
		if(n == 1) {
			return true;
		}
		 if(n <= 0) {
			 return false;
		 }
		 
		 if( n > 1) {
			 
			 if((n%2) == 0) {
				 
				 return (isPowerofTwo(n/2));
			 }
		 }
		 
		 return false;
	}

}
