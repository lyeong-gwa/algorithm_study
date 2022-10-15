import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(bf.readLine());
			input = new int[N];
			st = new StringTokenizer(bf.readLine());

			int max_len = 0;
			int day_1 = 0;
			int day_2 = 0;

			int total = 0;

			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
				max_len = Math.max(max_len, input[i]);
			}
			
			for (int i = 0; i < N; i++) {
				int ca = max_len - input[i];
				input[i] = ca;
				day_2 += ca/2;
				ca %= 2;
				day_1 += ca;
			}
//			System.out.println(Arrays.toString(input));
//			System.out.println(tc+":"+day_1+","+day_2);
			
			int init = Math.min(day_1, day_2);
			total+=init*2;
			day_1-=init;
			day_2-=init;
			
			int day=1;
			while(day_1>0||day_2>0) {
				if(day%2==1) {//홀수
					if(day_1>=1) {
						day_1--;
					}else if(day_2>=2){
						day_2--;
						day_1++;
					}
					total++;
					day=2;
				}else {//짝수
					if(day_2>=1) {
						day_2--;
					}
					total++;
					day=1;
				}
				
				
			}
			//System.out.println(day_1+","+day_2);
			System.out.println("#"+tc+" "+total);
		}
	}
}
