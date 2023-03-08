import java.util.Arrays;

public class InsertionSortRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      int arr[] = {10,1,7,4,8,2,11};
      int n= arr.length;
      
      System.out.println(Arrays.toString(arr));
      
      sortedArray(arr,1,n);
      System.out.println(Arrays.toString(arr));
      
	}
	
	public static void sortedArray(int[] arr,int i,int n) {
		
		//Base Condition
		if(n==0 || n==1 || i == n) {
			return;
		}
		
		
		int temp = arr[i];
		int j = i-1;
		while(j >= 0) {
			if(arr[j] > temp) {
				arr[j+1] = arr[j];
			}
			else {
				break;
			}
			
			j--;
		}
		
		arr[j+1] = temp;
		
		
		//Recursive call
		sortedArray(arr,i+1,n);
		
		
	}

}
