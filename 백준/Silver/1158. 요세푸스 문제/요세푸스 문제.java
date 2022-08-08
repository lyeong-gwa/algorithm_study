import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] tmp = br.readLine().split(" ");
		int N = Integer.parseInt(tmp[0]), K = Integer.parseInt(tmp[1]);
		ArrayList<Integer> table = new ArrayList<>();
		
		for(int i = 0 ; i< N ; i++) {
			table.add(i+1);
		}
		
		sb.append("<");
		int idx = 0;
		while(table.size()!=0) {
			idx+=(K-1);
			idx%=table.size();
			sb.append(table.get(idx));
			table.remove(idx);
			if(table.size()!=0) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb);

	}
}