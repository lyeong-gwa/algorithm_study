
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {//스도쿠
	static int[][] table;
	static ArrayList<Point> zero_list;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		table = new int[9][9];
		zero_list = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 9; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				if (table[i][j] == 0) {
					zero_list.add(new Point(i, j));
				}
			}
		}

		dfs(0);

	}

	static void dfs(int N) {
		if (N == zero_list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(table[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		Point target = zero_list.get(N);
		boolean[] able = able_arr(target.r,target.c);
		for(int i = 1; i < able.length;i++) {
			if(able[i]) {
				table[target.r][target.c] = i;
				dfs(N+1);
				table[target.r][target.c] = 0;
			}
		}
		
		
	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static boolean[] able_arr(int r, int c) {
		boolean[] able = new boolean[10];
		Arrays.fill(able, true);
		able[0] = false;

		for (int i = 0; i < 9; i++) {
			able[table[r][i]] = false;
			able[table[i][c]] = false;
		}
		int q_r = r / 3 * 3;
		int q_c = c / 3 * 3;
		for (int i = q_r; i < q_r + 3; i++) {
			for (int j = q_c; j < q_c + 3; j++) {
				able[table[i][j]] = false;
			}
		}
		
		return able;
	}

}


