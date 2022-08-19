import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		boolean[] check = new boolean[100001];
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int time = 0;
		
		queue.add(N);
		check[N] = true;
		
		while(!check[K]) {
			int size = queue.size();
			
			for(int i = 0 ; i < size; i++) {
				int tmp_num = queue.poll();
				if(range_check(tmp_num-1)&&!check[tmp_num-1]) {
					queue.add(tmp_num-1);
					check[tmp_num-1] = true;
				}
				if(range_check(tmp_num+1)&&!check[tmp_num+1]) {
					queue.add(tmp_num+1);
					check[tmp_num+1] = true;
				}
				if(range_check(tmp_num*2)&&!check[tmp_num*2]) {
					queue.add(tmp_num*2);
					check[tmp_num*2] = true;
				}
			}
			time++;
		}
		System.out.println(time);
	}
	public static boolean range_check(int num) {
		if(0<=num && num<=100000)
			return true;
		return false;
	}
}