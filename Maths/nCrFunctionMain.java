package Maths;
import java.util.*;

public class nCrFunctionMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter value of n and r: ");
		int n = scanner.nextInt();
		int r = scanner.nextInt();
		
				
		System.out.println(nCr(n,r));
		
	}
	
	static int nCr(int n, int r) {
		
		int num = findFactorial(n);
		int denom = (findFactorial(r) * findFactorial(n-r));
		
		
		return num/denom;
		
	}
	
	static int findFactorial(int n) {
		
		if(n == 0) {
			return 1;
		}
		
		return (n * (findFactorial(n-1)));
//		
//		long result = 1;
//		
//		for(long i = n; i>0; i--) {
//			result *= i;
//		}
//		
//		return result;
		
		
	}

	
}
