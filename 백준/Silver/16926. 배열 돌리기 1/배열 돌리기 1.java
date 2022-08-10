import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {// 16926
	static int[][] table;

	static int[] x_arr = { 1, 0, -1, 0 };
	static int[] y_arr = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
				R = Integer.parseInt(st.nextToken());
		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rotate(0, R);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(table[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void rotate(int cnt, int R) {

		if (Math.min(table.length, table[0].length) - (cnt * 2) == 0) {
			return;
		}

		int x = cnt, y = cnt;
		int xy_idx = 0;
		Deque<Integer> q = new LinkedList<>();

		for (int i = 0; i < table.length - 2 * cnt - 1; i++) {
			q.add(table[x][y]);
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table[0].length - 2 * cnt - 1; i++) {
			q.add(table[x][y]);
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table.length - 2 * cnt - 1; i++) {
			q.add(table[x][y]);
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table[0].length - 2 * cnt - 1; i++) {
			q.add(table[x][y]);
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}

		for (int i = 0; i < R; i++) {
			q.addFirst(q.pollLast());
		}

		xy_idx = 0;
		for (int i = 0; i < table.length - 2 * cnt - 1; i++) {
			table[x][y] = q.poll();
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table[0].length - 2 * cnt - 1; i++) {
			table[x][y] = q.poll();
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table.length - 2 * cnt - 1; i++) {
			table[x][y] = q.poll();
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		xy_idx++;
		for (int i = 0; i < table[0].length - 2 * cnt - 1; i++) {
			table[x][y] = q.poll();
			x += x_arr[xy_idx];
			y += y_arr[xy_idx];
		}
		rotate(cnt + 1, R);

	}

}
