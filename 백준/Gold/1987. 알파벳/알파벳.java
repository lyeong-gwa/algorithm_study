import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {// 1987
	static int[][] table;
	static int answer;
	static boolean[] alpa_list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		table = new int[N][M];
		alpa_list = new boolean['Z' - 'A'+1];
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for(int j = 0 ; j < tmp.length;j++) {
				table[i][j] = tmp[j]-'A';
			}
		}
		alpa_list[table[0][0]] =true;
		search(0, 0, 1);
		System.out.println(answer);
	}

	static void search(int x, int y, int cnt) {
		alpa_list[table[x][y]] = true;
		answer = Math.max(cnt, answer);
		if (check(x - 1, y)) {
			search(x - 1, y, cnt + 1);
		}
		if (check(x, y + 1)) {
			search(x, y + 1, cnt + 1);
		}
		if (check(x + 1, y)) {
			search(x + 1, y, cnt + 1);
		}
		if (check(x, y - 1)) {
			search(x, y - 1, cnt + 1);
		}
		alpa_list[table[x][y]] = false;
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < table.length && 0 <= y && y < table[0].length && !alpa_list[table[x][y]]) {
			return true;
		}
		return false;
	}
}