import java.util.*;
public class RatInMazeProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int arr[][] = {{1,0,0,0},{1,1,0,1},{1,1,0,0},{0,1,1,1}};
        int n = arr.length;
        
        System.out.println(findPath(arr,n));
	}
	
	public static ArrayList<String> findPath(int[][] arr,int n){
		
		ArrayList<String> ans = new ArrayList<>();
		
		if(arr[0][0] == 0) {
			return ans;
		}
		StringBuilder path = new StringBuilder();
		int srcx = 0;
		int srcy = 0;
		int[][] visited = new int[n][n];
		
		solve(arr,n,srcx,srcy,path,ans,visited);
		
		return ans;
	}
	
	public static void solve(int[][] arr,int n,int x,int y,StringBuilder path,ArrayList<String> ans,int[][] visited) {
		//Base condition
		if((x==n-1) && (y==n-1)) {
			ans.add(new String(path));
			return;
		}
		
		visited[x][y] = 1;
		
		//4 step - Down,Left,Right,Up
		//For Down Step		
		int newX = x+1;
		int newY = y;
		if(isSafe(arr,n,newX,newY,visited)) {
			path.append('D');
			
			//Recursive call
			solve(arr,n,newX,newY,path,ans,visited);
			
			path.deleteCharAt(path.length()-1);
		}
		
		//For Left Step		
		newX = x;
		newY = y-1;
		if(isSafe(arr,n,newX,newY,visited)) {
			path.append('L');
					
			//Recursive call
			solve(arr,n,newX,newY,path,ans,visited);
					
			path.deleteCharAt(path.length()-1);
		}
		
		//For Right Step		
		newX = x;
		newY = y+1;
		if(isSafe(arr,n,newX,newY,visited)) {
			path.append('R');
							
			//Recursive call
			solve(arr,n,newX,newY,path,ans,visited);
							
			path.deleteCharAt(path.length()-1);
		}
		
		//For Up Step		
		newX = x-1;
		newY = y;
		if(isSafe(arr,n,newX,newY,visited)) {
			path.append('U');
							
			//Recursive call
			solve(arr,n,newX,newY,path,ans,visited);
							
			path.deleteCharAt(path.length()-1);
		}
		
		
		visited[x][y] = 0;
	}
	
	public static boolean isSafe(int[][] arr,int n,int x,int y,int[][] visited) {
		
		if((x>=0 && x<n) && (y>=0 && y<n) && (arr[x][y] == 1) && (visited[x][y] == 0)) {
			return true;
		}else {
			return false;
		}
	}
}
