import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] table;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			count = 0;
			String[] tmp = br.readLine().split(" ");
			int M = Integer.parseInt(tmp[0]), N = Integer.parseInt(tmp[1]), K = Integer.parseInt(tmp[2]);

			table = new boolean[M + 2][N + 2];
			for (int i = 0; i < K; i++) {
				String[] x_y = br.readLine().split(" ");
				table[Integer.parseInt(x_y[0]) + 1][Integer.parseInt(x_y[1]) + 1]= true;
			}
			
			for(int i = 1 ; i<table.length-1;i++) {
				for(int j = 1; j<table[0].length-1;j++) {
					if(table[i][j]) {
						count++;
						remove(i,j);
					}
				}
			}
			
			
			sb.append(count+"\n");

		}
		System.out.println(sb);

	}

	public static void remove(int x, int y) {
		table[x][y] = false;
		int[] ch_x = {-1,0,0,1};
		int[] ch_y = {0,-1,1,0};
		
		for(int i = 0; i<4;i++) {
			if(table[x+ch_x[i]][y+ch_y[i]]) {
				remove(x+ch_x[i],y+ch_y[i]);
			}
		}
	}
}