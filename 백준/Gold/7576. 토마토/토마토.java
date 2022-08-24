import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 7576
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int answer = 0;
		int zero_cnt = 0;
		map = new int[R][C];
		Queue<Tomato> q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					zero_cnt++;
				} else if (map[i][j] == 1) {
					q.add(new Tomato(i, j));
				}
			}
		}
		
		if(zero_cnt==0) {
			System.out.println(0);
			System.exit(0);
		}
		
		

		while (!q.isEmpty()) {
			
//			System.out.println(q);
//			for(int[] tmp: map) {
//				System.out.println(Arrays.toString(tmp));
//			}
			
			int cycle = q.size();
			for (int i = 0; i < cycle; i++) {
				Tomato t = q.poll();
				int check_r = t.r+1,check_c = t.c;
				if(check(check_r,check_c)&&map[check_r][check_c]==0) { map[check_r][check_c]=1;q.add(new Tomato(check_r,check_c));zero_cnt--;}
				check_r = t.r;check_c = t.c+1;
				if(check(check_r,check_c)&&map[check_r][check_c]==0) { map[check_r][check_c]=1;q.add(new Tomato(check_r,check_c));zero_cnt--;}
				check_r = t.r-1;check_c = t.c;
				if(check(check_r,check_c)&&map[check_r][check_c]==0) { map[check_r][check_c]=1;q.add(new Tomato(check_r,check_c));zero_cnt--;}
				check_r = t.r;check_c = t.c-1;
				if(check(check_r,check_c)&&map[check_r][check_c]==0) { map[check_r][check_c]=1;q.add(new Tomato(check_r,check_c));zero_cnt--;}
			}
			answer++;
			if(zero_cnt==0) {
				System.out.println(answer);
				System.exit(0);
			}
			
		}
		System.out.println(-1);

	}

	public static boolean check(int r, int c) {
		if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) {
			return false;
		}
		return true;
	}
}

class Tomato {
	int r;
	int c;

	public Tomato(int r, int c) {
		this.r = r;
		this.c = c;
	}
}