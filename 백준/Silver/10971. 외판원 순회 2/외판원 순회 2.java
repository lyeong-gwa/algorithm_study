import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	static int[][] Edge;
	static boolean[] visit;
	static int[] number;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());

		visit = new boolean[N];
		Edge = new int[N][N];
		number = new int[N];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				Edge[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < N ;i++) {
			visit[i] = true;
			number[0] = i;
			perm(1, 0);
			visit[i] = false;
		}
		System.out.println(answer);
	}

	static void perm(int cnt,int price) {

		if(cnt==N) {
			int tmp_price = price + Edge[number[N-1]][number[0]];
			if(tmp_price!=price)
				answer = Math.min(answer, tmp_price);
			return;
		}
		for(int i = 0 ; i < N;i++) {
			if(visit[i]) continue;
			number[cnt] = i;
			visit[i] = true;
			int tmp_price = price+Edge[number[cnt-1]][number[cnt]];
			if(tmp_price<answer && tmp_price!=price) {
				perm(cnt+1,tmp_price);
			}
			visit[i] = false;
		}
	}

}
