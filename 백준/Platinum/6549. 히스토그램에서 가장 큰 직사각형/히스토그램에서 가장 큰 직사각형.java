import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {// 6549
	static int table[];
	static int N;
	static int[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			table = new int[N];
			
			for (int i = 0; i < N; i++) {
				table[i] = Integer.parseInt(st.nextToken());
			}
			

			int h= (int) Math.ceil(Math.log(N)/ Math.log(2));
			h = (int)Math.pow(2, h+1);
			tree = new int[h];
			init_tree(0,N-1,1);
			sb.append(getAnswer(0,N-1,N)+"\n");
		}
		System.out.println(sb);
	}

	public static long getAnswer(int start,int end,int N) {
		
		int point = search(0,N-1,1,start,end);
		long answer = (long)table[point]*(end-start+1);
		if((point-1)>=start) {
			answer = Math.max(getAnswer(start, point-1, N), answer);
		}
		if((point+1)<=end) {
			answer = Math.max(getAnswer(point+1, end, N), answer);
		}
		return answer;
	}
	
	
	public static int init_tree(int start,int end,int idx) {
		if(start==end) {
			tree[idx] = start;
			return tree[idx];
		}
		int mid = (start+end)/2;
		tree[idx] = table[init_tree(start,mid,idx*2)]>table[init_tree(mid+1,end,idx*2+1)]?init_tree(mid+1,end,idx*2+1):init_tree(start,mid,idx*2);
		return tree[idx];
	}

	public static int search(int start,int end, int idx, int left, int right) { //구간에서 가장 작은 인덱스 출력
		if(left>end || right< start) {
			return Integer.MAX_VALUE;
		}
		else if( left<=start && right >=end) {
			return tree[idx];
		}
		int mid = (start+end)/2;
		int left_val_idx = search(start,mid,idx*2,left,right);
		int right_val_idx = search(mid+1,end,idx*2+1,left,right);
		
		if(left_val_idx>=table.length) {
			return right_val_idx;
		} 
		if(right_val_idx >= table.length) {
			return left_val_idx;
		}
		
		return table[left_val_idx]<table[right_val_idx]?left_val_idx:right_val_idx;
			
	}
	
}