
public class QuickSortMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int arr[] = {2,4,1,6,9,5,3,5,3,5,3,5,3,5,7};
       int n = arr.length;
       
       quickSort(arr,0,n-1);
       
       for(int i : arr) {
    	   System.out.print(i + " ");
       }
	}
	
	public static void quickSort(int arr[],int start,int end) {
		
		//Base condition
		if(start >= end) {
			return;
		}
		
		//partition 
		int p = partition(arr,start,end);
		
		//sort left part
		quickSort(arr,start,p-1);
		
		//sort right part
		quickSort(arr,p+1,end);
		
	}
	
	public static int partition(int arr[],int start,int end) {
		int pivot = arr[start];
		
		int cnt = 0;
		for(int i=start+1; i<=end;i++) {
			if(arr[i] <= pivot) {
				cnt++;
			}
		}
		
		//place pivot at right position
		int pivotIndex = start + cnt;
		int temp = arr[start];
		arr[start] = arr[pivotIndex];
		arr[pivotIndex] = temp;
		
		//handle left and right part of array
		int i = start, j = end;
		
		while(i<pivotIndex && j>pivotIndex) {
			
			while(arr[i]<=pivot) {
				i++;
			}
			while(arr[j] > pivot) {
				j--;
			}
			
			if(i<pivotIndex && j>pivotIndex) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
				i++;
				j--;
			}
		}
		
		return pivotIndex;
		
		
	}

}
