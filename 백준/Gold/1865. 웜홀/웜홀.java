import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			table = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(table[i], N * 10000); 
				table[i][i] = 0;
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(bf.readLine());
				int s = Integer.parseInt(st.nextToken())-1;
				int e = Integer.parseInt(st.nextToken())-1;
				int t = Integer.parseInt(st.nextToken());

				table[s][e] = Math.min(table[s][e], t);
				table[e][s] = Math.min(table[e][s], t);

			}

			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(bf.readLine());
				int s = Integer.parseInt(st.nextToken())-1;
				int e = Integer.parseInt(st.nextToken())-1;
				int t = -Integer.parseInt(st.nextToken());

				table[s][e] = Math.min(table[s][e], t);
			}
			

			for(int k=0;k<N;k++) {
				for(int i = 0 ; i < N;i++) {
					for(int j = 0 ; j < N;j++) {
						table[i][j] = Math.min(table[i][j],table[i][k]+table[k][j]);
					}
				}
			}
			

			
			boolean check = true;
			for(int i = 0 ; i < N;i++) {
				if(table[i][i]!=0) {
					check = false;
					break;
				}
			}
			
			if(check) {
				System.out.println("NO");
			}else {
				System.out.println("YES");
			}
			

		}

	}

}