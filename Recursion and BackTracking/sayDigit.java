
public class sayDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String arr[] = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		
      int n = 412;
      sayDigit(n,arr);
	}
	
	public static void sayDigit(int n,String[] num) {
		
		
		if(n==0) {
			return;
		}
		
		int digit = n % 10;
		
        n = n/10;
		
		sayDigit(n,num);
		
		System.out.print(num[digit]+ "  ");
		
		
		
	
	}

}
