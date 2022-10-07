
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static boolean[][] table;
	static int[] cnt_list;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#"+tc+" ");
			int N = Integer.parseInt(bf.readLine());
			int M = Integer.parseInt(bf.readLine());

			table = new boolean[N][N];
			cnt_list = new int[N];
			for(int i = 0 ; i<N;i++) {
				Arrays.fill(table[i], false);
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				table[a][b] = true;
			}
			
			for(int k=0; k<N;k++) {
				for(int i = 0 ; i < N;i++) {
					for(int j = 0 ; j < N;j++) {
						table[i][j] = table[i][j]|(table[i][k]&&table[k][j]);
					}
				}
			}
			int answer = 0;
			for(int i=0;i<N;i++) {
				int cnt=0;
				for(int j=0;j<N;j++) {
					if(table[i][j]|table[j][i]) {
						cnt++;
					}
				}
				if(cnt+1==N) {
					answer++;
				}
			}
			sb.append(answer+"\n");

		}
		System.out.println(sb);

	}

}
