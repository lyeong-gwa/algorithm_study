import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] l_r = bf.readLine().split(" ");
		int N = Integer.parseInt(l_r[0]), M = Integer.parseInt(l_r[1]);
		StringTokenizer st;
		int[] input = new int[N+1];
		st = new StringTokenizer(bf.readLine());
		
		input[0] = 0;
		for(int i = 1; i <= N;i++) {
			input[i] = Integer.parseInt(st.nextToken())+input[i-1];
		}
		
		for(int m = 0; m < M;m++) {
			String[] tmp = bf.readLine().split(" ");
			int x = Integer.parseInt(tmp[0]), y = Integer.parseInt(tmp[1]);
			sb.append(input[y]-input[x-1]+"\n");
			
		}
		System.out.println(sb);
	}
}