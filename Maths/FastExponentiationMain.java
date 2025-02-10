package Maths;

import java.util.*;

public class FastExponentiationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number a:");
		int a = scan.nextInt();
		System.out.println("Enter a number b:");
		int b = scan.nextInt();

		int ans = power(a, b);
		System.out.println("Result is: " + ans);
	}

	/*
	 * Constraints:
	 * 
	 * -100.0 < a < 100.0
	 * -10^9 <= b <= 10^9
	 * Either a is not zero or b > 0.
	 * -104 <= (a^b) <= 104
	 */

	public static int power(int a, int b) {

		// Base condition
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		// If b is negative
		if (b < 0) {
			return (1 / power(a, -b));
		}

		int ans = power(a, b / 2);

		if ((b % 2) == 0) {
			return ans * ans;
		} else {
			return a * ans * ans;
		}

	}

}
