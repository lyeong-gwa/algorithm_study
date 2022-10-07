import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] table;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] dist;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = -Integer.parseInt(st.nextToken());
			}
		}

		int union = 0;
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (table[i][j] == -1) {
					union++;
					table[i][j] = union;
					q.add(new Point(i, j));
					while (!q.isEmpty()) {
						Point tmp = q.poll();
						for (int k = 0; k < 4; k++) {
							int next_r = tmp.r + dr[k];
							int next_c = tmp.c + dc[k];
							if (isIn(next_r, next_c) && table[next_r][next_c] == -1) {
								table[next_r][next_c] = union;
								q.add(new Point(next_r, next_c));
							}
						}

					}
				}
			}
		}
		dist = new int[union][union];
		for (int i = 0; i < union; i++) {
			Arrays.fill(dist[i], 1000);
		}

		for (int i = 0; i < N; i++) {
			int start = -1;
			int start_idx = 0;

			for (int j = 0; j < M; j++) {
				if (table[i][j] != 0) {
					if (start == -1) { // 한번도 만난적 없음
						start = table[i][j];
						start_idx = j;
					} else if (start == table[i][j] || (j - start_idx) <= 2) {// 같은 동지 + 길이1 다리밖에 안될 때
						start = table[i][j];
						start_idx = j;
					} else {
						dist[start - 1][table[i][j] - 1] = Math.min(dist[start - 1][table[i][j] - 1],
								j - start_idx - 1);
						dist[table[i][j] - 1][start - 1] = Math.min(dist[table[i][j] - 1][start - 1],
								j - start_idx - 1);
						start = table[i][j];
						start_idx = j;
					}
				}
			}

		}

		for (int j = 0; j < M; j++) {
			int start = -1;
			int start_idx = 0;

			for (int i = 0; i < N; i++) {
				if (table[i][j] != 0) {
					if (start == -1) { // 한번도 만난적 없음
						start = table[i][j];
						start_idx = i;
					} else if (start == table[i][j] || (i - start_idx) <= 2) {// 같은 동지 + 길이1 다리밖에 안될 때
						start = table[i][j];
						start_idx = i;
					} else {
						dist[start - 1][table[i][j] - 1] = Math.min(dist[start - 1][table[i][j] - 1],
								i - start_idx - 1);
						dist[table[i][j] - 1][start - 1] = Math.min(dist[table[i][j] - 1][start - 1],
								i - start_idx - 1);
						
						start = table[i][j];
						start_idx = i;
					}
				}
			}

		}

		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.cost - e2.cost;
		});
		for (int i = 0; i < union; i++) {
			for (int j = i; j < union; j++) {
				if (dist[i][j] != 1000) {
					pq.add(new Edge(i, j, dist[i][j]));
				}
			}
		}

		parent = new int[union];
		for (int i = 0; i < union; i++) {
			parent[i] = i;
		}
		int answer = 0;
		while (!pq.isEmpty()) {
			Edge tmp = pq.poll();
			if (find(tmp.s) != find(tmp.d)) {
				answer += tmp.cost;
				UNION(tmp.s, tmp.d);
			}
		}
		for(int i = 0 ; i < union;i++) {
			parent[i] = find(i);
		}
		boolean check_all=true;
		for(int i = 0 ; i < union;i++) {
			if(parent[0]!=parent[i]) {
				check_all=false;
				break;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(table[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < union; i++) {
//			System.out.println(Arrays.toString(dist[i]));
//		}
//		System.out.println(union);
		
		
		if(check_all) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}

	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	static void UNION(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	static boolean isIn(int r, int c) {
		int N = table.length;
		int M = table[0].length;
		if (r >= 0 && c >= 0 && r < N && c < M) {
			return true;
		}
		return false;
	}

	static class Edge {
		int s;
		int d;
		int cost;

		public Edge(int s, int d, int cost) {
			this.s = s;
			this.d = d;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [s=" + (s+1) + ", d=" + (d+1) + ", cost=" + cost + "]";
		}

	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}