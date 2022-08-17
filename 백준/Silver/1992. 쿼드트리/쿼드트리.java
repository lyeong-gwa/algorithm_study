import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {// 1992
	static char[][] table;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		table = new char[N][N];
		
		int init_cnt = 0;
		for (int i = 0; i < N; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				table[i][j] = tmp[j];
				if(table[i][j]=='1') {
					init_cnt++;
				}
			}
		}
		if(!(init_cnt==0||init_cnt==N*N)) {
			sb.append("(");
			write_table(0, 0, N);
			sb.append(")");
		}
		else if(init_cnt==0) {
			sb.append(0);
		}else {
			sb.append(1);
		}
		System.out.println(sb);
	}

	public static void write_table(int x, int y, int n) {
		if (n == 1) {
			System.out.print(table[x][y]);
			return;
		}
		int cnt;

		cnt = 0;
		for (int i = x; i < (x + n / 2); i++) {
			for (int j = y; j < (y + n / 2); j++) { // 1
				if (table[i][j] == '1') {
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			sb.append(0);
		} else if (cnt == (n * n / 4)) {
			sb.append(1);
		} else {
			sb.append("(");
			write_table(x, y, n / 2);
			sb.append(")");
		}

		cnt = 0;
		for (int i = x; i < (x + n / 2); i++) {
			for (int j = (y + n / 2); j < (y + n); j++) {// 2
				if (table[i][j] == '1') {
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			sb.append(0);
		} else if (cnt == (n * n / 4)) {
			sb.append(1);
		} else {
			sb.append("(");
			write_table(x, y + n / 2, n / 2);
			sb.append(")");
		}

		cnt = 0;
		for (int i = (x + n / 2); i < (x + n); i++) {
			for (int j = y; j < (y + n / 2); j++) { // 3
				if (table[i][j] == '1') {
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			sb.append(0);
		} else if (cnt == (n * n / 4)) {
			sb.append(1);
		} else {
			sb.append("(");
			write_table(x + n / 2, y, n / 2);
			sb.append(")");
		}

		cnt = 0;
		for (int i = (x + n / 2); i < (x + n); i++) {
			for (int j = (y + n / 2); j < (y + n); j++) {
				if (table[i][j] == '1') {
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			sb.append(0);
		} else if (cnt == (n * n / 4)) {
			sb.append(1);
		} else {
			sb.append("(");
			write_table(x + n / 2, y + n / 2, n / 2);
			sb.append(")");
		}

	}
}