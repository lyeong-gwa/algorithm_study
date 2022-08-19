import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int[][] table;
	static int[][] table_copy;
	
	static int N;
	static int M;
	static int D;
	static int max_score;
	static int total;
	static Unit[] input;
	static Unit[] numbers;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		table = new int[N + 1][M];
		table_copy = new int[N + 1][M];
		
		max_score = 0;
		numbers = new Unit[3];
		input = new Unit[M];
		for(int i = 0 ; i < M ; i++) {
			input[i] = new Unit(N,i,D,N,M);
			Collections.sort(input[i].p_list,(e1,e2)->{
				if(e1.priortiy==e2.priortiy) {
					return e1.y-e2.y;
				}else {
					return e1.priortiy-e2.priortiy;
				}
			});
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				if (table[i][j] != 0)
					total++;
			}
		}
		combi(0,0);
		
		System.out.println(max_score);
		
	}
	public static void combi(int cnt, int start) {
		if (cnt == 3) {
			for(int i = 0 ; i < table.length;i++) {
				for(int j = 0 ; j < table[0].length;j++) {
					table_copy[i][j] = table[i][j];
				}
			}
			int target = game_start();
			max_score = Math.max(target, max_score);
			return;
		}
		for (int i = start; i < M; i++) {
			numbers[cnt] = input[i];
			combi(cnt + 1, i + 1);
		}
	}
	

	public static int game_start() {
		int score = 0;
		int run = 0;
		int cnt =3;
		while (score + run != total) {
			score += attack();
			run += go();
		}
		return score;
	}

	public static int go() {
		int cnt = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				table_copy[i + 1][j] = table_copy[i][j];
				table_copy[i][j] = 0;
			}
		}
		for (int i = 0; i < M; i++) {
			if (table_copy[N][i] >= 1) {
				if(table_copy[N][i]==1) {
					cnt++;
				}
			}
			table_copy[N][i] = 0;
		}
		
		return cnt;
	}

	public static int attack() {
		int cnt = 0;
		List<Point> die = new ArrayList<>();
		for(int i = 0 ; i < 3 ; i++) {
			for(Point tmp : numbers[i].p_list) {
				if(table_copy[tmp.x][tmp.y]>=1) {
					if(table_copy[tmp.x][tmp.y] == 1) {
						cnt++;
					}
					table_copy[tmp.x][tmp.y] = 2;
					die.add(new Point(tmp.x,tmp.y));
					break;
				}
			}
		}
		
		for(Point d:die) {
			table_copy[d.x][d.y] = 0;
		}
		
		return cnt;
	}
}

class Unit extends Point {
	int d;
	List<Point> p_list;

	public Unit(int x, int y, int d, int N, int M) {
		super(x, y);
		this.d = d;
		p_list = new ArrayList<>();
		make_plist(N, M);
	}

	public void make_plist(int N,int M) {
		for(int r = y-d ; r <= y+d ;r++) {
			for(int c = x; c >= x-d ;c--) {
				int dist = (Math.abs(r-y)+Math.abs(c-x));
				if( 0<=r && r<M && 0<=c && c < N && dist<=d) {
					Point in = new Point(c,r);
					in.priortiy = dist;
					p_list.add(in);
				}
			}
		}
		
	}
}

class Point {
	int x;
	int y;
	int priortiy;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		priortiy = 0;
	}

}
