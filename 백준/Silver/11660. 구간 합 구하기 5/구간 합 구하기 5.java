import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] l_r = bf.readLine().split(" ");
		int N = Integer.parseInt(l_r[0]), M = Integer.parseInt(l_r[1]);
		int[][] table = new int[N+1][N+1];

		StringTokenizer st;
		for(int i = 0; i<table.length;i++) {
			table[i][0]=0;
			table[0][i]=0;

		}
		
		for (int i = 1; i < table.length; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j < table.length; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				table[i][j] += (table[i-1][j]+table[i][j-1]-table[i-1][j-1]);
			}
			
			
		}
		
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(bf.readLine());
			int target = 0;
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			target = table[x2][y2]+table[x1-1][y1-1]-table[x2][y1-1]-table[x1-1][y2];
			sb.append(target + "\n");
		}
		System.out.println(sb);
	}
}
