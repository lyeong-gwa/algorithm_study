import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Node start;
	static Node end;
	static ArrayList<Node> list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(bf.readLine());

			list = new ArrayList<>();

			st = new StringTokenizer(bf.readLine());
			start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(start);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			st = new StringTokenizer(bf.readLine());
			end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(end);

			int[][] dist = new int[list.size()][list.size()];

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < i; j++) {
					int len = list.get(i).len(list.get(j));
					if(len<=1000) {
						dist[i][j] = len;
						dist[j][i] = len;
					}else if(i!=j){
						dist[i][j] = 1000000;
						dist[j][i] = 1000000;
					}
				}
			}
			
			Queue<Integer> q = new LinkedList<>();
			q.add(0);
			list.get(0).visit=true;
			while(!q.isEmpty()) {
				int tmp = q.poll();
				
				for(int i = 0 ; i < list.size();i++) {
					if(dist[tmp][i]<=1000&&!list.get(i).visit) {
						q.add(i);
						list.get(i).visit=true;
					}
				}
			}
			if(end.visit) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
			

		}

	}

	static class Node {
		int r;
		int c;
		boolean visit;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
			visit = false;
		}
		public int len(Node node) {
			return Math.abs(r-node.r)+Math.abs(c-node.c);
		}
		
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
		
		
		
	}

}