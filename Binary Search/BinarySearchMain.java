import java.util.*;

public class BinarySearchMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int even[] = {2,4,6,8,12,18};
		int odd[] = {3,8,11,14,16};
		
		
		int oddIndex =  binarySearch(odd,5,3);
		
		System.out.println(Arrays.toString(odd));
		
		System.out.println(oddIndex);
		
        int evenIndex =  binarySearch(even,6,1);
		
		System.out.println(Arrays.toString(even));
		
		System.out.println(evenIndex);
		 
	}
	
	static int binarySearch(int arr[], int size, int key) {
		
		int start = 0;
		int end = size - 1;		
		
		while(start <= end) {

			int mid = (start + ((end - start)/2));

			if(key == arr[mid]) {
				return mid;
			}
			
			if(key > arr[mid]) {
				
				start = mid + 1;				
				 
			} else {	
				
				end = mid - 1;			
				
			} 			
		}
		
		return -1;
	}

}
