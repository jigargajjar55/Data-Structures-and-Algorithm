package BinarySearch;
public class MedianOfTwoSortedArrays_LC4 {


    //Binary Search
    //Time: O(log (min(M, N))),
    //Space: O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int mLength = nums1.length;
        int nLength = nums2.length;

        if(mLength > nLength){
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = mLength;
        int totalLength = mLength + nLength;

        int leftPart = ((mLength + nLength + 1) / 2);

        while(start <= end){
            int mid1 = start + ((end - start) / 2);
            int mid2 = leftPart - mid1;

            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if(mid1 < mLength){
                r1 = nums1[mid1];
            }
            if(mid2 < nLength){
                r2 = nums2[mid2];
            }

            if(mid1 - 1 >= 0){
                l1 = nums1[mid1 - 1];
            } 
            if(mid2 - 1 >= 0){
                l2 = nums2[mid2 - 1];
            }

            if(l1 <= r2 && l2 <= r1){
                if((totalLength & 1) == 1){
                    return (double)(Math.max(l1, l2));
                }else{
                    return (double)((Math.max(l1,l2) + Math.min(r1,r2)) / 2.0);
                }
            }else if(l1 > r2){
                end = mid1 - 1;
            }else{
                start = mid1 + 1;
            }
        }


        return 0;
        
    }


    //Space Optimised Approach
    //Time: O(M + N),
    //Space: O(1)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        int l = m + n;
        int ptr2 = l / 2;
        int val2 = 0;
        int ptr1 = ptr2 - 1;
        int val1 = 0;

        int count = 0;
        int i = 0;
        int j = 0;
        while(count <= ptr2 && i < m && j < n){
            if(nums1[i] < nums2[j]){
                if(count == ptr1){
                    val1 = nums1[i];
                }
                if(count == ptr2){
                    val2 = nums1[i];
                }
               
                i++;
            }else{

                if(count == ptr1){
                    val1 = nums2[j];
                }
                if(count == ptr2){
                    val2 = nums2[j];
                }
               
                j++;

            }
            count++;
        }

        while(count <= ptr2 && i < m){
            if(count == ptr1){
                val1 = nums1[i];
            }
            if(count == ptr2){
                val2 = nums1[i];
            }
            count++;
            i++;
        }

        while(count <= ptr2 && j < n){
            if(count == ptr1){
                val1 = nums2[j];
            }
            if(count == ptr2){
                val2 = nums2[j];
            }
            count++;
            j++;
        }


        if((l & 1) == 1){
            return (double)(val2);
        }else{
            double ans = (((double)val1 + (double)val2)/2);
            return ans;
        }
        
    }
    

    
    //Merge 2 sorted array and construct Array
    //Then find median, if Array Length is odd or even

    //Time: O(M + N),
    //Space: O(M + N)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        int l = m + n;
        int index = 0;
        int[] nums = new int[l];

        int i = 0;
        int j = 0;

        while(i < m && j < n){

            if(nums1[i] < nums2[j]){
                nums[index] = nums1[i];                
                i++;
            }else{
                nums[index] = nums2[j];               
                j++;
            }
            index++;
        }
        while(i < m){
            nums[index] = nums1[i]; 
            index++;               
            i++;
        }
        while(j < n){
            nums[index] = nums2[j];   
            index++;            
            j++;
        }

        if((l & 1) == 1){
            int medianIndex = l / 2;

            return (double)(nums[medianIndex]);
        }else{
            int medianIndex2 = l / 2;
            int medianIndex1 = medianIndex2 - 1;

            double ans = ((double)nums[medianIndex1] + (double)nums[medianIndex2])/2;
            return ans;

        }

        
    }
}
