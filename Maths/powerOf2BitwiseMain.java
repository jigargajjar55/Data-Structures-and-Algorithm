package Maths;
import java.util.Scanner;

public class powerOf2BitwiseMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number: ");
		int number = scanner.nextInt();	

		
		System.out.println(isPowerofTwo(number));
	}

	static boolean isPowerofTwo(int n) {
		
		int numberofDigits = 0;
	
		while(n != 0) {
			
			if((n & 1) == 1) {
				numberofDigits += 1;
			}
			
			n = n >> 1;			
		}
		
		return numberofDigits == 1;
		
		
	}
	
}
