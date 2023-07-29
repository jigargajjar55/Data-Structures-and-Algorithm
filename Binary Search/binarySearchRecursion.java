
public class binarySearchRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int arr[] = {2,3,4,5,6,7,8,9};
       int end = arr.length-1;
       int key = 6;
       
       boolean ans = isPresent(arr,0,end,key);
       
       if(ans) {
    	   System.out.println("Key is present!");
       }else {
    	   System.out.println("Key is not present!!");
       }
       
	}
	
	public static boolean isPresent(int[] arr,int start,int end,int key) {
		//base condition
		if(start > end) {
			return false;
		}
		
		//Processing
		int mid = start + ((end-start)/2);
		
		if(arr[mid] == key) {
			return true;
		}
		else if(arr[mid] > key) {
			
			return isPresent(arr,start,mid-1,key);
		} else {
				
			return isPresent(arr,mid+1,end,key);
		}
		
	}

}
