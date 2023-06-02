
public class FindPivotinArrayMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = {3,4,5,6,7,8, 10, 17, 1};
		
		int arrLength = arr.length;
		
		System.out.println("Pivot Index is "+ getPivot(arr,arrLength));
		
		System.out.println("Pivot Index of Array using BS Recursion "+ getPivotRecursion(arr,0,arrLength-1));
		
	}
	
	static int getPivot(int[] arr, int size) {
		
		int start = 0;
		int end = size - 1;
		
		int mid = start + ((end-start)/2);
		
		while(start < end) {
			
			if(arr[mid] >= arr[0]) {
				
				start = mid + 1;
				
			} else {
				end = mid;
			}
			
			mid = start + ((end-start)/2);
		}
		
		return start;
	}

	static int getPivotRecursion(int[] arr,int start, int end) {
		
		//Base Condition
		if(start >= end) {
			return start;
		}
		int mid = start + ((end-start)/2);
		
		if(arr[mid] >= arr[0]) {
			return getPivotRecursion(arr,mid+1,end);
		}else {
			return getPivotRecursion(arr,start,mid);
		}
		
	}
}
