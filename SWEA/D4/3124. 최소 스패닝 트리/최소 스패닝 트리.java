import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {// 3124 , 7465
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(bf.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			parent = new int[V];
			for(int i = 0 ; i < V;i++) {
				parent[i] = i;
			}
			long answer = 0;
			PriorityQueue<Edge> table = new PriorityQueue<>((e1, e2) -> {
				return e1.weight - e2.weight;
			});
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine());
				int s = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				table.add(new Edge(s, d, w));
			}

			while (!table.isEmpty()) {
				Edge tmp = table.poll();

				if (find(tmp.s) != find(tmp.d)) {
					answer += tmp.weight;
					union(tmp.s, tmp.d);
				}
			}
			sb.append(answer);
			sb.append("\n");

		}
		System.out.println(sb);

	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

}

class Edge {
	int s;
	int d;
	int weight;

	public Edge(int a, int b, int c) {
		s = a;
		d = b;
		weight = c;
	}

}