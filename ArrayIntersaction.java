import java.util.*;
class ArrayIntersectionMain {

	
	//Brute Force Solution
	static List<Integer> arrayInterSection(int[] arrayA, int[] arrayB){
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i=0; i<arrayA.length; i++) {
			int element = arrayA[i];
			
			for(int j=0; j< arrayB.length; j++) {
				
				if(element == arrayB[j]) {
					result.add(element);
					arrayB[j] = Integer.MIN_VALUE;
					break;
				}
			}
			
		}
		
		return result;
	}

	//Optimized Solution
	static List<Integer> arrayInterSect(int[] arr1,int n, int[] arr2, int m){
		
		List<Integer> result = new ArrayList<Integer>();
		
		int i = 0;
		int j = 0;
		while(i<n && j<m) {
			
			if(arr1[i] == arr2[j]) {
				result.add(arr1[i]);
				i +=1;
				j += 1;				
			} else if(arr1[i] < arr2[j]) {
				i += 1;
			} else {
				j += 1;
				
			}
		}
		
		return result;
		
	}


    public static void main(String[] args) {
        
		 int arrayA[] = {1,2,3,3,4,4,5,6};
		 int aL = arrayA.length;
		 int arrayB[] = {2,2,4,4,6};
		 int bL = arrayB.length;
			
		 System.out.println(Arrays.toString(arrayA));
		 System.out.println(Arrays.toString(arrayB));
		
		 List<Integer> result = arrayInterSect(arrayA,aL,arrayB,bL); 
		 
		 System.out.println(result);
	}
	

}