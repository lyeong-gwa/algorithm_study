import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		List<Integer> table = new ArrayList<>();
		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		if(M!=0) {
			st = new StringTokenizer(bf.readLine());
			for(int i = 0 ; i < M ; i++) {
				table.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		
		
		int answer = Math.abs(N-100);
		int up = N;
		int down = N;
		while(!check(table,up)&& (N+answer)>=up) {
			up++;
		}
		if(up!=N) {
			int tmp = Integer.toString(up).toCharArray().length+(up-N);
			answer = Math.min(answer, tmp);
		}
		
		while(!check(table,down)&&down>=0&&(N-answer)<=down) {
			down--;
		}
		if(down>=0) {
			int tmp = Integer.toString(down).toCharArray().length+(N-down);
			answer = Math.min(answer, tmp);
		}
		System.out.println(answer);

		
	}
	static boolean check(List<Integer> table, int input) {
		char[] input_c = Integer.toString(input).toCharArray();
		for(char tmp:input_c) {
			if(table.contains(tmp-'0')) {
				return false;
			}
		}
		return true;
	}
	
}