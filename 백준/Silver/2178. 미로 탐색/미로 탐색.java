import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String trash = bf.readLine();
		int x = Integer.parseInt(trash.split(" ")[0]), y = Integer.parseInt(trash.split(" ")[1]);
		ArrayList<Point> stack = new ArrayList<>();
		table = new int[x][y];
		for (int i = 0; i < x; i++) {
			char[] st = bf.readLine().toCharArray();
			for (int j = 0; j < st.length; j++) {
				table[i][j] = Integer.parseInt(st[j] + "");
			}
		}
		///////////////////////////////////////////////////////////////
		stack.add(new Point(0, 0));
		table[0][0] = -1;
		System.out.println(solution(stack, 1));
	}

	public static int solution(ArrayList<Point> stack, int cnt) {
		int[][] look = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		ArrayList<Point> tmp_stack = new ArrayList<>();
		for (int i = 0; i < stack.size(); i++) {
			Point st = stack.get(i);
			for (int[] look_tmp : look) {
				int tmp_x = look_tmp[0] + st.x;
				int tmp_y = look_tmp[1] + st.y;
				if (tmp_x == table.length - 1 && tmp_y == table[0].length - 1) {
					return cnt + 1;
				}

				if (check(tmp_x, tmp_y)) {
					table[tmp_x][tmp_y] = -1;
					tmp_stack.add(new Point(tmp_x, tmp_y));
				}
			}
		}
		return solution(tmp_stack, cnt + 1);

	}

	public static boolean check(int x, int y) {
		if (x < 0 || x >= table.length || y < 0 || y >= table[0].length || table[x][y] != 1) {
			return false;
		}
		return true;
	}
}