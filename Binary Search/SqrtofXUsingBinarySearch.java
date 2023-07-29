import java.util.*;
public class SqrtofXUsingBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number to find sqrt: ");
		int inputNumber = scanner.nextInt();
		
		int tempAnswer = sqrtInteger(inputNumber);
		
		System.out.println(tempAnswer);
		
		int tempRecursionAnswer = sqrtIntegerRecursion(inputNumber,0,inputNumber);
		
		System.out.println(tempRecursionAnswer);
		
		//System.out.println(morePrecision(inputNumber,3,tempAnswer));
		
		
		
	}
	
	static double morePrecision(int n, int precision,int tempSol) {
		
		double factor = 1;
		double ans = tempSol;
		
		for(int i=0; i<precision; i++) {
			factor = factor/10;
			
			for(double j = ans; j*j < n; j = j+factor ) {
				ans = j;
			}
		}
		return ans;
	}
	
	static int sqrtInteger(int number) {
		
		long start = 0;
		long end = number;
		long ans = 0;
		
		long mid = start + ((end-start)/2);
		
		while(start <= end) {
			
			long square = mid * mid;
			
			if(square > number) {
				end = mid - 1;
			} else if(square < number) {
				ans = mid;
				start = mid + 1;
			} else {
				return (int)mid;
			}
			mid = start + ((end-start)/2);
		}
		
		return (int)ans;
		
	}

	static int sqrtIntegerRecursion(int number,int start,int end) {
		
		long ans = 0;
		//Base condition
		if(start > end) {
			return (int)ans;
		}
		
		long mid = start + ((end-start)/2);
		
		if((mid * mid) < number) {
			ans = mid;
			int temp = sqrtIntegerRecursion(number,(int)mid+1,end);
			if(temp != 0) {
				return temp;
			}
			return (int)ans;
			
		}else if((mid * mid) > number) {
			return sqrtIntegerRecursion(number,start,(int)mid-1);
		}else {
			return (int)mid;
		}
		
		
		
	}
}
