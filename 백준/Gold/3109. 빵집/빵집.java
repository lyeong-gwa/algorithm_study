import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main{
	static char[][] table;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		table = new char[N][M];
		answer = 0;
		for(int i = 0;i<N;i++) {
			table[i] = bf.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < N ; i++) {
			search(i,0);
		}
		sb.append(answer+ " ");
		System.out.println(sb);
	}
	public static boolean search(int x, int y) {
		if(y==table[0].length-1) {
			answer++;
			return true;
		}
		for(int i = -1; i<2;i++) {
			if(0<=x+i&&x+i<table.length&&(table[x+i][y+1]=='.')) {
				table[x+i][y+1] = '*';
				if(search(x+i,y+1)) {
					return true;
				}
			}
		}
		
		
		return false;
	}
}