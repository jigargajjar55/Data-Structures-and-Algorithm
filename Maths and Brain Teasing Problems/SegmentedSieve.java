import java.util.*;
public class SegmentedSieve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number: ");
		Scanner scan = new Scanner(System.in);
		 int n = scan.nextInt();
	     System.out.println("Primes smaller than " + n + ":");
	     segmentedSieve(n);
	}
	
	public static void segmentedSieve(int n) {
		// Compute all primes smaller than or equal to square root of n using simple sieve
		int limit = (int)(Math.floor(Math.sqrt(n))+1);
		ArrayList<Integer> prime = new ArrayList<>();
		simpleSieve(limit,prime);
		
		 // Divide the range [0..n-1] in different segments We have chosen segment size as sqrt(n).
		int low = limit;
		int high = 2*limit;
		
		
		while(low < n) {
			if(high >= n) {
				high = n;
			}
			
			boolean mark[] = new boolean[limit+1];
			for(int i=0;i<prime.size();i++) {
				// Find the minimum number in [low..high] that is
                // a multiple of prime.get(i) (divisible by prime.get(i))
                // For example, if low is 31 and prime.get(i) is 3,
                // we start with 33.
                int loLim = (int) (Math.floor(low/prime.get(i)) * prime.get(i));
                if (loLim < low)
                    loLim += prime.get(i);
      
                /*  Mark multiples of prime.get(i) in [low..high]:
                    We are marking j - low for j, i.e. each number
                    in range [low, high] is mapped to [0, high-low]
                    so if range is [50, 100]  marking 50 corresponds
                    to marking 0, marking 51 corresponds to 1 and
                    so on. In this way we need to allocate space only
                    for range  */
                for (int j=loLim; j<high; j+=prime.get(i))
                    mark[j-low] = true;
            }
      
            // Numbers which are not marked as false are prime
            for (int i = low; i<high; i++)
                if (mark[i - low] == false)
                    System.out.print(i + "  ");
      
            // Update low and high for next segment
            low  = low + limit;
            high = high + limit;
			}
		}
		
	
	
	public static void simpleSieve(int limit, ArrayList<Integer> prime) {
		boolean mark[] = new boolean[limit+1];
		
		for(int i=2; (i*i)<limit;i++) {
			
			if(mark[i]==false) {
				
				for(int j=i*i;j<limit;j+=i) {
					mark[j] = true;
				}
			}
			
		}
		
		for(int i=2;i<limit;i++) {
			if(mark[i]==false) {
				System.out.print(i+ " ");
			}
		}
	}

}
