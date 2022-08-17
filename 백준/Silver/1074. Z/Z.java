import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		check(0, 0, (int) Math.pow(2, N), r, c);
	}

	static void check(int x, int y, int size, int r, int c) {
		if (x==r && y==c) {
			System.out.println(answer);
			return;
		}
		if(x<=r && r<x+size&& y<=c && c<y+size) {
			int half = size/2;
			check(x,y,half,r,c);
			check(x,y+half,half,r,c);
			check(x+half,y,half,r,c);
			check(x+half,y+half,half,r,c);
		}else {
			answer+=size*size;
		}

	}

}