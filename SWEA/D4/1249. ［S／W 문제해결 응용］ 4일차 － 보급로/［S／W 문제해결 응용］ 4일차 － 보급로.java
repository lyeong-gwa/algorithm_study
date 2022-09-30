
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Solution{//1249
	static int[] rotate_r = {-1,0,1,0};
	static int[] rotate_c = {0,1,0,-1};
	
	
	static int[][] table;
	static int[][] min_val;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(bf.readLine());
			table = new int[N][N];
			min_val = new int[N][N];
			
			for(int i = 0; i < N ; i++) {
				char[] input = bf.readLine().toCharArray();
				for(int j = 0 ; j < N ; j++) {
					table[i][j] = input[j]-'0';
					min_val[i][j] = Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<Point> pq = new PriorityQueue<>((e1,e2)->{
				return e1.move-e2.move;
			});
			pq.add(new Point(0,0,0));
			while(!pq.isEmpty()) {
				Point tmp = pq.poll();
				if(tmp.r==N-1&&tmp.c==N-1) {
					sb.append("#").append(tc+" ").append(tmp.move+"\n");
					break;
				}
				for(int i = 0 ; i < 4 ;i++) {
					int cr = rotate_r[i]+tmp.r;
					int cc = rotate_c[i]+tmp.c;
					if((cr>=0 && cr<N &&cc >=0 && cc<N)&&(min_val[cr][cc]>table[cr][cc]+tmp.move)) {
						min_val[cr][cc] = table[cr][cc]+tmp.move;
						pq.add(new Point(cr,cc,min_val[cr][cc]));
					}
				}
				
				
			}

			
		}
		System.out.println(sb);
		
	}

	
	static class Point{
		int r;
		int c;
		int move;
		public Point(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
		
	}
	
	
}