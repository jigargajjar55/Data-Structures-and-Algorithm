
public class isSortedRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int arr[] = {2,3,5,5,5};
       int size = arr.length;
       boolean ans = isSorted(arr,0,size);
       if(ans) {
    	   System.out.println("Array is sorted");
       }else {
    	   System.out.println("Array is not sorted");
       }
       
	}
	
	public static boolean isSorted(int[] arr,int start,int size) {
		
		
		if(size == 0 || start == size-1) {
			return true;
		}
		
		if(arr[start] > arr[start+1]) {
			return false;
		}
		else {
			boolean remainingPart = isSorted(arr,start+1,size);
			return remainingPart;
		}
		
		
		
	}

}
