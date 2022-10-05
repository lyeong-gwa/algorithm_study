import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] table;
	static boolean[][][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		table = new char[N + 2][M + 2];
		visit = new boolean[64][N + 2][M + 2];
		State start = null;

		for (int i = 1; i < table.length - 1; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 1; j < table[0].length - 1; j++) {
				table[i][j] = tmp[j - 1];
				if (table[i][j] == '0') {
					start = new State(i, j, 0, 0);
					table[i][j] = '.';
				}
			}
		}
		for (int i = 0; i < table.length; i++) {
			table[i][0] = '#';
			table[i][table[0].length - 1] = '#';
		}
		for (int i = 0; i < table[0].length; i++) {
			table[0][i] = '#';
			table[table.length - 1][i] = '#';
		}

		Queue<State> q = new LinkedList<>();
		q.add(start);
		visit[0][start.r][start.c] = true;

		while (!q.isEmpty()) {
			State cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int next_r = cur.r + dr[i];
				int next_c = cur.c + dc[i];

				if (isIn(next_r, next_c)) {
					int next_key = make_key(cur.key_val, table[next_r][next_c]);
					if(check_able(next_r,next_c,next_key,cur.move)) {
						State tmp_state = new State(next_r,next_c,cur.move+1,next_key);
						visit[next_key][next_r][next_c] = true;
						q.add(tmp_state);
					}
				}

			}
		}
		System.out.println(-1);

	}

	static boolean check_able(int r,int c,int key_val,int move) {
		if(visit[key_val][r][c]||table[r][c]=='#') { //방문한적 있다.
			return false;
		}else if(table[r][c]=='.') {
			return true;
		}else if(table[r][c]>='a'&&table[r][c]<='f') {
			return true;
		}else if(table[r][c]>='A'&&table[r][c]<='F') {
			int tf = key_val&(int) Math.pow(2, (table[r][c] - 'A'));
			if(tf==0) {
				return false;
			}else {
				return true;
			}
			
		}else if(table[r][c]=='1'){
			System.out.println(move+1);
			System.exit(0);
		}
		return false;
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < table.length && c < table[0].length) {
			return true;
		}
		return false;
	}

	static int make_key(int cur_key, char table_val) {
		if (table_val >= 'a' && table_val <= 'f') {
			return cur_key | (int) Math.pow(2, (table_val - 'a'));
		}
		return cur_key;
	}

	static class State {
		int r;
		int c;
		int move;
		int key_val;

		public State(int r, int c, int move, int key_val) {
			this.r = r;
			this.c = c;
			this.move = move;
			this.key_val = key_val;
		}

		@Override
		public String toString() {
			return "State [r=" + r + ", c=" + c + ", move=" + move + ", key_val=" + key_val + "]";
		}
		
	}

}