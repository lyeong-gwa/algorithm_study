import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		PriorityQueue<String> pq = new PriorityQueue<>((e1,e2)->{
			if(e1.length() != e2.length()) {
				return e1.length()-e2.length();
			}else {
				return e1.compareTo(e2);
			}
		}) ;
		
		for(int i = 0 ; i < T;i++) {
			pq.add(bf.readLine());
		}
		
		String record="A";
		for(int i = 0 ; i < T;i++) {
			String tmp = pq.poll();
			if(record.equals(tmp)) {
				continue;
			}
			record = tmp;
			System.out.println(tmp);
		}
		

	}
}