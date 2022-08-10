import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 16926
	static int[][] table;
	static int[][] dump_table;
	static int[][] cmd;
	static int min;
	static boolean[] isSelect;
	static int[] input, numbers;

	public static void main(String[] args) throws Exception {
		min = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
				R = Integer.parseInt(st.nextToken());

		input = new int[R];
		numbers = new int[R];
		isSelect = new boolean[R];

		table = new int[N][M];
		dump_table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < R; i++) {
			input[i] = i;
			isSelect[i] = false;

		}

		cmd = new int[R][3];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd[i][0] = Integer.parseInt(st.nextToken());
			cmd[i][1] = Integer.parseInt(st.nextToken());
			cmd[i][2] = Integer.parseInt(st.nextToken());
		}
		perm(0, R);
		System.out.println(min);
	}

	public static void rotate(int cnt_x, int cnt_y, int s) {
		if (s < 1) {
			return;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		int x = cnt_x - s, y = cnt_y - s;
		q.add(dump_table[x][y]);
		y++;
		for (; y < cnt_y + s; y++) {
			q.add(dump_table[x][y]);
			dump_table[x][y] = q.poll();
		}
		for (; x < cnt_x + s; x++) {
			q.add(dump_table[x][y]);
			dump_table[x][y] = q.poll();
		}
		for (; y > cnt_y - s; y--) {
			q.add(dump_table[x][y]);
			dump_table[x][y] = q.poll();
		}
		for (; x > cnt_x - s; x--) {
			q.add(dump_table[x][y]);
			dump_table[x][y] = q.poll();
		}
		dump_table[x][y] = q.poll();

		rotate(cnt_x, cnt_y, s - 1);

	}

	public static void perm(int cnt, int R) {
		if (cnt == R) {

			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table[0].length; j++) {
					dump_table[i][j] = table[i][j];
				}
			}

			for (int i = 0; i < numbers.length; i++) {
				rotate(cmd[numbers[i]][0] - 1, cmd[numbers[i]][1] - 1, cmd[numbers[i]][2]);
			}
			int tmp_min = Integer.MAX_VALUE;
			for (int i = 0; i < table.length; i++) {
				int row_sum = 0;
				for (int j = 0; j < dump_table[i].length; j++) {
					row_sum += dump_table[i][j];
				}
				tmp_min = tmp_min > row_sum ? row_sum : tmp_min;
			}
			min = min > tmp_min ? tmp_min : min;
			return;
		}

		for (int i = 0; i < R; i++) {
			if (isSelect[i])
				continue;
			numbers[cnt] = input[i];
			isSelect[i] = true;
			perm(cnt + 1, R);
			isSelect[i] = false;

		}

	}
}