import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] mem = new int[N+K-1];

		int start = 0;
		for (int i = 0; i < mem.length; i++) {
			if(i<N) {
				mem[i] = Integer.parseInt(bf.readLine());
			}else {
				mem[i] = mem[i%N];
				continue;
			}
		}

		int[] window = new int[D+1];
		
		int window_cnt = 0;
		//초기값
		for(int i = 0; i < K;i++) {
			if(window[mem[i]]==0) {
				window_cnt++;
			}
			window[mem[i]]++;
		}
		if(window[C]==0) {
			window_cnt++;
		}
		window[C]++;
		
		int answer = window_cnt;

		for (int i = 1; i < N; i++) {
			if(window[mem[i-1]]==1) {
				window_cnt--;
			}
			window[mem[i-1]]--;
			if(window[mem[i+K-1]]==0) {
				window_cnt++;
			}
			window[mem[i+K-1]]++;
			
			answer = Math.max(answer,window_cnt);
		}
		System.out.println(answer);
	}


}