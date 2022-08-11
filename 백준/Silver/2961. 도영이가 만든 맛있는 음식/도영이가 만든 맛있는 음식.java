import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	static int[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());

		StringTokenizer st;
		answer = Integer.MAX_VALUE;
		table = new int[N][2];
		for (int i = 0; i < table.length; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			table[i][0] = Integer.parseInt(st.nextToken());
			table[i][1] = Integer.parseInt(st.nextToken());

		}
		sol(0, 1, 0, 0);
		System.out.println(answer);
	}

	public static void sol(int cnt, int sin, int ssn, int select) {
		if (select > 0) {
			answer = answer > Math.abs(sin - ssn) ? Math.abs(sin - ssn) : answer;
		}
		if (cnt >= table.length)
			return;
		sol(cnt + 1, sin * table[cnt][0], ssn + table[cnt][1], select + 1); // 해당 cnt를 선택하고 넘어감
		sol(cnt + 1, sin, ssn, select); // 해당 cnt를 선택하지 않고 넘어감
	}

}