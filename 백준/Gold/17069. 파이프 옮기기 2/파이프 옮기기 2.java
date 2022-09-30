import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static Node[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());

		table = new Node[N][N];

		// 입력값 받아오기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1")) {
					table[i][j] = new Node(0, 0, 0, true);
				} else {
					table[i][j] = new Node(0, 0, 0, false);
				}
			}
		}

		// 기저조건
		table[0][1] = new Node(1, 0, 0, false);
		for (int i = 2; i < N; i++) {// 행 = 0 인 노드 2부터 시작 옆에 가로있고 자신이 벽이 아니면 1추가,
			if (table[0][i].wall == false) {
				table[0][i].h += table[0][i - 1].sum();
			}
		}

		
		if (table[1][2].wall == false && table[0][2].wall == false && table[1][1].wall == false) {
			table[1][2].d++;
		}
		for (int i = 2; i < N; i++) {// 열 = 2인 노드 세로밖에 안놓여지니 처리하기
			if (table[i][2].wall == false) {
				table[i][2].v += table[i - 1][2].sum();
			}
		}

		for (int i = 1; i < table.length; i++) {
			for (int j = 3; j < table.length; j++) {
				if(table[i][j].wall==false) {
					table[i][j].v = table[i-1][j].d + table[i-1][j].v;
					table[i][j].h = table[i][j-1].h + table[i][j-1].d;
					
					if(table[i-1][j].wall==false && table[i][j-1].wall==false) {
						table[i][j].d = table[i-1][j-1].sum();
					}
					
					
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(table[i][j].sum() + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(table[N-1][N-1]);
		
		System.out.println(table[N-1][N-1].sum());
	}



	static class Node {
		long h;// 가로
		long v;// 세로
		long d;// 대각선
		boolean wall;

		public Node(long h, long v, long d, boolean wall) {
			this.h = h;
			this.v = v;
			this.d = d;
			this.wall = wall;
		}

		public long sum() {
			return h + v + d;
		}

		@Override
		public String toString() {
			return "Node [h=" + h + ", v=" + v + ", d=" + d + ", wall=" + wall + "]";
		}
		
		
	}

}
