import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int total;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int input = Integer.parseInt(br.readLine());
		hanoi(input, 1,2,3);
		System.out.println(total);
		System.out.println(sb);
		
	}
	
	static void hanoi(int N,int start,int other ,int end) {
		if(N==1) {
			sb.append(start + " "+ end+"\n");
			total++;
		}
		else {
			hanoi(N-1,start,end,other);
			sb.append(start + " "+ end+"\n");
			total++;
			hanoi(N-1,other,start,end);
		}
	} 
	
	
}