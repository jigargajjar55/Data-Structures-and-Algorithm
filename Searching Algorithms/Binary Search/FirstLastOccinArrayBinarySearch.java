import java.util.Arrays;

public class FirstLastOccinArrayBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int array[] = {1,2,3,3,3,3,3,3,3,3,3,3,3,5};
		
		System.out.println(Arrays.toString(array));
		int arrayLength = array.length;
		
		int result[] = new int[2];
		result[0] = firstOcc(array,arrayLength,3);
		result[1] = lastOcc(array,arrayLength,3);
		
		System.out.println(Arrays.toString(result));
	}
	
	static int firstOcc(int[] arr, int size, int key) {
		
		int start = 0,end = size -1;
		
		int mid = start + ((end-start)/2);
		int ans = -1;
		
		while(start <= end) {
			
			if(arr[mid] == key) {
				ans = mid;
				end = mid - 1;
			}else if(arr[mid] > key) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
			
			mid = start + ((end-start)/2);
		}	
		
		return ans;
	}
	
   static int lastOcc(int[] arr, int size, int key) {
		
		int start = 0,end = size -1;
		
		int mid = start + ((end-start)/2);
		int ans = -1;
		
		while(start <= end) {
			
			if(arr[mid] == key) {
				ans = mid;
				start = mid + 1;
			}else if(arr[mid] > key) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
			
			mid = start + ((end-start)/2);
		}	
		
		return ans;
	}

}
