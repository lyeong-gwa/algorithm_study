import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 3055
	static char[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int S_r = 0,S_c = 0;
		map = new char[R][C];
		int answer = 0 ;
		
		Queue<Node> q_hog= new LinkedList<>();  
		Queue<Node> q_water= new LinkedList<>(); 
		
		for (int i = 0; i < R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp[j];
				if(map[i][j]=='S') { //고슴도치 위치
					q_hog.add(new Node(i,j));
				}
				else if(map[i][j]=='*') { //물위치
					q_water.add(new Node(i,j));
				}
				else if(map[i][j]=='D') {//굴 위치
					S_r = i;
					S_c = j;
				}
				
			}
		}
		while(true) {
//			System.out.println(q_hog);
//			System.out.println(q_water);			
//			for(char[] tmp: map) {
//				System.out.println(Arrays.toString(tmp));
//			}
//			System.out.println();
			
			int q_hog_size = q_hog.size();
			int q_water_size = q_water.size();
			if(q_water_size==0&&q_hog_size==0) {
				System.out.println("KAKTUS");
				System.exit(0);
			}
				
			for(int i = 0 ; i < q_water_size;i++) {
				Node target = q_water.poll();
				
				if(check(target.r-1,target.c)&&map[target.r-1][target.c]=='.') {map[target.r-1][target.c] = '*';q_water.add(new Node(target.r-1,target.c));}
				if(check(target.r,target.c-1)&&map[target.r][target.c-1]=='.') {map[target.r][target.c-1] = '*';q_water.add(new Node(target.r,target.c-1));}
				if(check(target.r+1,target.c)&&map[target.r+1][target.c]=='.') {map[target.r+1][target.c] = '*';q_water.add(new Node(target.r+1,target.c));}
				if(check(target.r,target.c+1)&&map[target.r][target.c+1]=='.') {map[target.r][target.c+1] = '*';q_water.add(new Node(target.r,target.c+1));}
			}
			for(int i = 0 ; i < q_hog_size;i++) {
				Node target = q_hog.poll();
				
				if(target.r==S_r&&target.c==S_c) { //고슴도치가 굴에 먼저 도달하였다.
					System.out.println(answer);
					System.exit(0);
				}
				if(check(target.r-1,target.c)&&(map[target.r-1][target.c]=='.'||map[target.r-1][target.c]=='D')) {map[target.r-1][target.c] = 'S';q_hog.add(new Node(target.r-1,target.c));}
				if(check(target.r,target.c-1)&&(map[target.r][target.c-1]=='.'||map[target.r][target.c-1]=='D')) {map[target.r][target.c-1] = 'S';q_hog.add(new Node(target.r,target.c-1));}
				if(check(target.r+1,target.c)&&(map[target.r+1][target.c]=='.'||map[target.r+1][target.c]=='D')) {map[target.r+1][target.c] = 'S';q_hog.add(new Node(target.r+1,target.c));}
				if(check(target.r,target.c+1)&&(map[target.r][target.c+1]=='.'||map[target.r][target.c+1]=='D')) {map[target.r][target.c+1] = 'S';q_hog.add(new Node(target.r,target.c+1));}
			}
			answer++;
		}
		

	}
	
	
	
	public static boolean check(int r, int c) {
		if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) {
			return false;
		}

		return true;
	}
	
}

class Node{
	int r;
	int c;
	public Node(int r,int c) {
		this.r = r;
		this.c = c;
	}
	@Override
	public String toString() {
		return "Node [r=" + r + ", c=" + c + "]";
	}
	
}