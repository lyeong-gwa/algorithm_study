import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Edge> table;
	static int[] input;
	static boolean[] select;
	static int N;
	static int answer;
	static int[] parent;
	static int cnt_answer;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());
		table = new ArrayList<Edge>();
		select = new boolean[N];
		answer = Integer.MAX_VALUE;
		input = new int[N];
		cnt_answer = 0;
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());

			for (int j = 0; j < num; j++) {
				table.add(new Edge(i, Integer.parseInt(st.nextToken()) - 1));
			}

		}

		subset(0, 0);
		if(cnt_answer==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		a = parent[a];
		b = parent[b];

		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;

	}

	static void subset(int idx, int cnt) {
		if (idx == N) {
			if (!(cnt != 0 && cnt != N)) {
				return;
			}
			Queue<Edge> table2 = new LinkedList<>();
			ArrayList<Integer> A = new ArrayList<>();
			ArrayList<Integer> B = new ArrayList<>();

			for (Edge tmp : table) {
				table2.add(tmp);
			}

			int a_sum = 0;
			int b_sum = 0;
			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < N; i++) {
				if (select[i]) {
					A.add(i);
					a_sum += input[i];
				} else {
					B.add(i);
					b_sum += input[i];
				}
			}

			while (!table2.isEmpty()) {
				Edge tmp = table2.poll();

				if (A.contains(tmp.s) == A.contains(tmp.d)) {
					if (find(tmp.s) != find(tmp.d)) {
						union(tmp.s, tmp.d);
					}
				}
			}

			boolean check = true;
			
			for(int i = 0; i < N-1;i++) {
				parent[i] = find(parent[i]);
			}
			
			
			for (int i = 0; i < A.size(); i++) {
				if (check) {
					for (int j = 0; j < A.size(); j++) {
						if (parent[A.get(i)] != parent[A.get(j)]) {
							check = false;
						}
					}
				}
			}
			if(check) {
				for (int i = 0; i < B.size(); i++) {
					if (check) {
						for (int j = 0; j < B.size(); j++) {
							if (parent[B.get(i)] != parent[B.get(j)]) {
								check = false;
							}
						}
					}
				}
			}
			if(check) {
//				System.out.println();
//				System.out.println(Arrays.toString(parent));
//				System.out.println(A.toString()+"->" + a_sum);
//				System.out.println(B.toString()+"->" + b_sum);
				answer = Math.min(Math.abs(b_sum-a_sum),answer);
				cnt_answer++;
			}
			else {
//
//				System.out.println();
//				System.out.println(Arrays.toString(parent));
//				System.out.println(A.toString()+"->" + a_sum);
//				System.out.println(B.toString()+"->" + b_sum);
			}
			

			return;
		}

		select[idx] = true;
		subset(idx + 1, cnt + 1);
		select[idx] = false;
		subset(idx + 1, cnt);

	}

}

class Edge {
	int s;
	int d;

	public Edge(int a, int b) {
		s = a;
		d = b;
	}

	@Override
	public String toString() {
		return "Edge [s=" + s + ", d=" + d + "]";
	}

}

/*

10
1 2 3 4 5 6 7 8 9 10 
1 2 
1 3 
1 4 
1 5 
1 6 
1 7 
1 8 
1 9 
1 10 
1 1


 */
