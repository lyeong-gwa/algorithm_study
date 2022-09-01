import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws  Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		System.out.println(bottomUp(N));
	}
	
	static int bottomUp(int num) {
		int[] dp = new int[num+1];
		
		for(int i=2; i<num+1; i++) {
			dp[i] = dp[i-1]+1;
			if(i%2==0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
			if(i%3==0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
		}
		return dp[num];
	}
}