
public class linearSearchRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int arr[] = {1,4,8,2,9};
        int size = arr.length;
        int key = 0;
        
        boolean ans = isPresent(arr,0,size,key);
        if(ans) {
        	System.out.println("Key is present!");
        }else {
        	System.out.println("Key is not present!!");
        }
	}
	
	public static boolean isPresent(int[] arr,int start,int size,int key) {
		
		if(start == size) {
			return false;
		}
		
		if(arr[start] == key) {
			return true;
		} else {
			boolean ans = isPresent(arr,start+1,size,key);
			return ans;
		}
	}

}
