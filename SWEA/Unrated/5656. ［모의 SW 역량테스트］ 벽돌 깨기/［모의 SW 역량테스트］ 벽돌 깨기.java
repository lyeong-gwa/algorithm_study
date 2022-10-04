
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			answer = 0;
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			Block[][] origin = new Block[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < W; j++) {
					origin[i][j] = new Block(i, j, Integer.parseInt(st.nextToken()));
					if (origin[i][j].val > 0) {
						answer++;
					}
				}
			}
			
			BFS(origin, N,answer);

			sb.append("#" + tc + " ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void BFS(Block[][] table, int N,int cnt) {
		answer = Math.min(cnt, answer);
		if(N==0) {
			return;
		}
		N--;
		for (int i = 0; i < table[0].length; i++) {
			int idx = 0;
			while (idx < table.length && table[idx][i].val == 0) {
				idx++;
			}
			if (idx<table.length&&table[idx][i].val != 0) {
				Block[][] tmp_table = new Block[table.length][table[0].length];
				for(int r = 0 ; r < table.length;r++) {
					for(int c = 0 ; c < table[0].length;c++) {
						tmp_table[r][c] = new Block(r,c,table[r][c].val);
					}
				}
				int break_block_cnt = BOOM(idx,i,tmp_table,0);
				sort_block(tmp_table);
				BFS(tmp_table,N,cnt-break_block_cnt);
			}
		}

		
	}

	static int BOOM(int r, int c, Block[][] table,int cnt) {
		int value = table[r][c].val;
		table[r][c].val = 0;
		cnt++;
		if (value > 1) {
			for (int i = r - (value - 1); i < r + value; i++) {
				if (i >= 0 && i < table.length && table[i][c].val > 0) {
					cnt = BOOM(i, c, table,cnt);
				}
			}

			for (int i = c - (value - 1); i < c + value; i++) {
				if (i >= 0 && i < table[r].length && table[r][i].val > 0) {
					cnt = BOOM(r, i, table,cnt);
				}
			}
		}
		return cnt;
	}

	static void sort_block(Block[][] table) {
		for (int c = 0; c < table[0].length; c++) {
			for (int r = table.length - 1; r >= 0; r--) {
				if (table[r][c].val == 0) {
					for (int next = r - 1; next >= 0; next--) {
						if (table[next][c].val != 0) {
							table[r][c].val = table[next][c].val;
							table[next][c].val = 0;
							break;
						}
					}
				}
			}

		}
	}

	static void print_table(Block[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j].val + " ");
			}
			System.out.println();
		}
	}

	static class Block {
		int r;
		int c;
		int val;

		public Block(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}

	}
}
