import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int time;
	
	static int[] record;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] table;
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		table = new int[N][M];
		
		record = new int[M];
		
		
		for(int i = 0 ; i < N;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0 ; j < M ;j++) {
				table[i][j] = tmp[j]-'0';
			}
		}
		
		time = 0;
		for(int i = N-1; i >=0 ; i--) {
			for(int j = M-1; j>=0;j--) {
				
				if(check(table[i][j]+record[j])) {
					time++;
					for(int a=0; a <= j ;a++) ++record[a];
//					System.out.println("뒤집기");
				}
//				System.out.println(i+","+j+":"+(table[i][j]+record[j]));
//				System.out.println(Arrays.toString(record));
//				System.out.println();
			}
		}
		System.out.println(time);
	}
	static boolean check(int target) {
		return target%2!=0;
	}

}