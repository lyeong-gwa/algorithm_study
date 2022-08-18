import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
public class Solution{//1247.최적경로
	static int N;
	static Point[] user_list;
	static Point[] select_list;
	static int answer;
	static Point enter;
	static Point home;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <=T;tc++) {
			N = Integer.parseInt(bf.readLine());
			st = new StringTokenizer(bf.readLine());
			enter = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			user_list = new Point[N];
			select_list = new Point[N];
			answer = Integer.MAX_VALUE;
			for(int i = 0 ; i < N ; i ++) {
				user_list[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			bit_perm(0,0);
			System.out.println("#"+tc+" "+answer);
			
		}
		
	}
	public static void bit_perm(int cnt,int flag) {
		if (cnt == N) {
			int tmp = 0;
			for(int i = 0 ; i < N-1 ; i++) {
				tmp += select_list[i].dist(select_list[i+1]);
			}
			tmp += enter.dist(select_list[0]);
			tmp += home.dist(select_list[N-1]);
			
			answer = answer>tmp?tmp:answer;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1<<i) != 0)
				continue;
			select_list[cnt] = user_list[i];
			bit_perm(cnt + 1,flag|1<<i);

		}

	}
}
class Point{
	int x;
	int y;
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int dist(Point p) {
		return Math.abs(x-p.x)+Math.abs(y-p.y);
	}
}