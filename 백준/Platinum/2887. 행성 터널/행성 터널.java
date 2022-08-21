import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer = 0;
		int N = Integer.parseInt(bf.readLine());
		Unit[] table = new Unit[N];
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			table[i] = new Unit(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), i);
			parent[i] = i;
		}

		List<Unit> table_x = new ArrayList<>(Arrays.asList(table));
		List<Unit> table_y = new ArrayList<>(Arrays.asList(table));
		List<Unit> table_z = new ArrayList<>(Arrays.asList(table));
		table_x.sort((e1, e2) -> {
			return e1.x - e2.x;
		});
		table_y.sort((e1, e2) -> {
			return e1.y - e2.y;
		});
		table_z.sort((e1, e2) -> {
			return e1.z - e2.z;
		});
		PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> {
			return e1.score - e2.score;
		});
		for (int i = 0; i < N - 1; i++) {
			Unit u1 = table_x.get(i);
			Unit u2 = table_x.get(i + 1);
			queue.add(new Edge(u1, u2));
			u1 = table_y.get(i);
			u2 = table_y.get(i + 1);
			queue.add(new Edge(u1, u2));
			u1 = table_z.get(i);
			u2 = table_z.get(i + 1);
			queue.add(new Edge(u1, u2));
		}

		while (!queue.isEmpty()) {
			Edge tmp = queue.poll();
			
			
			if (!isSameParent(tmp.s, tmp.d)) {
                answer += tmp.score;
                union(tmp.s, tmp.d);
            }
		}

		System.out.println(answer);
	}

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    private static boolean isSameParent(int a, int b) {
        return find(a) == find(b);
    }

}

class Unit {
	int x;
	int y;
	int z;
	int id;

	public Unit(int x, int y, int z, int id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}

	public int dist(Unit o) {
		return Math.min(Math.abs(x - o.x), Math.min(Math.abs(y - o.y), Math.abs(z - o.z)));

	}

}

class Edge {
	int s;
	int d;
	int score;

	public Edge(Unit u1, Unit u2) {
		s = u1.id;
		d = u2.id;
		score = u1.dist(u2);
	}

}