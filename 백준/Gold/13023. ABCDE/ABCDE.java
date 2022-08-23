import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
public class Main{
	public static Node[] tree;
	public static int answer;
	public static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		tree = new Node[N];
		visit = new boolean[N];
		answer = 0;
		for(int i = 0 ; i <N ;i++) {
			tree[i] = new Node();
		}
		for(int i = 0 ; i <M ;i++) {
			st = new StringTokenizer(bf.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tree[left].next.add(right);
			tree[right].next.add(left);
		}
		for(int i = 0 ; i < N;i++) {
			visit[i] = true;
			search_depth(1,i);
			visit[i] = false;
		}
		System.out.println(answer);
		
	}
	public static int search_depth(int cnt,int idx) {
		if(answer==1) return cnt;
		int max = cnt;
		for(int i = 0 ; i < tree[idx].next.size();i++) {
			int target = tree[idx].next.get(i);
			if(!visit[target]) {
				visit[target] = !visit[target];
				max = Math.max(search_depth(cnt+1,target), max);
				if(max>=5) {
					answer = 1;
				}
				visit[target] = !visit[target];
			}
			if(answer==1){
				break;
			}
			
			
		}
		return max;
	}

}
class Node{
	List<Integer> next;
	public Node(){
		next = new ArrayList<>();
	}
	@Override
	public String toString() {
		return "Node [next=" + next + "]";
	}
	
}