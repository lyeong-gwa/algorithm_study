import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());

			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
					if (dist[i][j] == 0 && i != j) {
						dist[i][j] = 1000000;
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			int min = 1000000;
			for (int i = 0; i < N; i++) {
				int tmp = 0;
				for (int j = 0; j < N; j++) {
					tmp+=dist[i][j];
				}
				min=Math.min(min, tmp);
			}
			sb.append("#"+tc+" "+ min+"\n");

		}
		System.out.println(sb);

	}

}