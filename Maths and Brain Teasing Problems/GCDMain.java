import java.util.*;
public class GCDMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a and b numbers: ");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		scanner.close();
		System.out.println("GCD of "+a+" and "+b+" is: "+ euclidGCD(a,b));
		
	}
	
	public static int gcd(int a,int b) {
		
		if(a==0) {
			return b;
		}
		if(b==0) {
			return a;
		}
		
		while(a != b) {
			if(a > b) {
				a = a-b;
			}else {
				b = b-a;
			}
		}
		
		return a;
		
	}

	public static int euclidGCD(int a,int b) {
		if(b==0) {
			return a;
		}
		
		return euclidGCD(b, a % b);
	}
	
	public static int getGCD(int a,int b) {
		//Base condition
		if(a==0) {
			return b;
		}
		if(b==0) {
			return a;
		}
		
		if(a > b) {
			return getGCD(b,a % b);
		}
		else {
			return getGCD(a, b % a);
		}
		
		
	}
	
}
