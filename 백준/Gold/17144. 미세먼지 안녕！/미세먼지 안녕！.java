import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Node[][] table;
	static int R;
	static int C;
	static ArrayList<Point> UP;
	static ArrayList<Point> DOWN;
	static int air_clear;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		table = new Node[R][C];
		UP = new ArrayList<>();
		DOWN = new ArrayList<>();
		answer = 0;
		int air_clear = 0; // 아래쪽 공기청정기 위치
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < C; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				table[i][j] = new Node(tmp);
				if (table[i][j].value == -1) {
					if (table[i - 1][j].value == -1) { // 아래쪽 이라는 뜻이된다.
						air_clear = i;
					}

				}
				else {
					answer += tmp;
				}
			}
		}

		// 위쪽사이클
		MakeUP(air_clear - 2, 0, air_clear);
		MakeDOWN(air_clear + 1, 0, air_clear);

//		System.out.println(UP);
//		System.out.println(DOWN);

		// 이동포인트 설정
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				table[i][j].make_next_point(i, j, R, C, air_clear);
			}
		}

		for(int i = 0 ; i < T ; i++) {
			move();
			sum_value();
			MOVE(UP);
			MOVE(DOWN);
		}

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(table[i][j].value + " ");
//			}
//			System.out.println();
//		}
		System.out.println(answer);
	}
	public static void MOVE(ArrayList<Point> cycle) {
		int remove = table[cycle.get(0).r][cycle.get(0).c].value;
		for(int i = 0 ; i < cycle.size()-1;i++) {
			Point now = cycle.get(i);
			Point next = cycle.get(i+1);
			table[now.r][now.c].value = table[next.r][next.c].value; 
		}
		table[cycle.get(cycle.size()-1).r][cycle.get(cycle.size()-1).c].value = 0;
		answer-= remove;
	}
	
	

	public static void MakeUP(int r, int c, int air_clear) {
		while (true) {
			UP.add(new Point(r, c));
			if (r == 0) {
				break;
			}
			r--;
		}
		c++;
		while (true) {
			UP.add(new Point(r, c));
			if (c + 1 == C)
				break;
			c++;
		}
		r++;
		while (true) {
			UP.add(new Point(r, c));
			if (r + 1 == air_clear) {
				break;
			}
			r++;
		}
		c--;
		while (true) {
			UP.add(new Point(r, c));
			if (c - 1 == 0) {
				break;
			}
			c--;
		}
	}

	public static void MakeDOWN(int r, int c, int air_clear) {
		while (true) {
			DOWN.add(new Point(r, c));
			if (r + 1 == R) {
				break;
			}
			r++;
		}
		c++;
		while (true) {
			DOWN.add(new Point(r, c));
			if (c + 1 == C)
				break;
			c++;
		}
		r--;
		while (true) {
			DOWN.add(new Point(r, c));
			if (r == air_clear) {
				break;
			}
			r--;
		}
		c--;
		while (true) {
			DOWN.add(new Point(r, c));
			if (c - 1 == 0) {
				break;
			}
			c--;
		}
	}

	public static void move() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				table[i][j].move(table);
			}
		}
	}

	static void sum_value() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				table[i][j].value += table[i][j].next_value;
				table[i][j].next_value = 0;
			}
		}
	}

}

class Node {
	static int[] rotate_r = { -1, 0, 1, 0 };
	static int[] rotate_c = { 0, 1, 0, -1 };

	ArrayList<Point> point;
	int value;
	int next_value;

	public Node(int value) {
		point = new ArrayList<>();
		this.value = value;
		next_value = 0;

	}

	public void make_next_point(int r, int c, int len_R, int len_C, int air_clear) {
		if (c == 0) {
			if (r == air_clear || r == air_clear - 1) {
				return;
			}
		}
		for (int i = 0; i < 4; i++) {
			int target_r = r + rotate_r[i];
			int target_c = c + rotate_c[i];
			if (target_r < 0 || target_r >= len_R || target_c < 0 || target_c >= len_C) { // 맵 밖이면 무시한다.
				continue;
			} else if ((target_r == air_clear && target_c == 0) || (target_r == (air_clear - 1) && target_c == 0)) { // 공기청정기여도
																														// 무시한다.
				continue;
			} else {
				point.add(new Point(target_r, target_c));
			}
		}
	}

	public void move(Node[][] table) {
		int move_val = value / 5;
		value -= move_val * point.size();
		for (Point tmp : point) {
			table[tmp.r][tmp.c].next_value += move_val;
		}
	}
}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + "]";
	}

}