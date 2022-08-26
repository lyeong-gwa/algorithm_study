import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[][] table;
	static int answer;
	static boolean[][] visit;
	static int[][] price;
	static int[] rotate_r = { -1, 0, 1, 0 };
	static int[] rotate_c = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int TC = 0;
		while ((N = Integer.parseInt(bf.readLine())) != 0) {
			answer = 0;
			table = new int[N][N];
			visit = new boolean[N][N];
			price = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
					price[i][j] = 10000;
				}
			}

			PriorityQueue<Node> pq = new PriorityQueue<Node>((e1, e2) -> {
				return e1.value - e2.value;
			});

			// 처음위치
			pq.add(new Node(0, 0, table[0][0]));
			visit[0][0] = true;
			price[0][0] = table[0][0];
			
			
			
			while (!pq.isEmpty()) {
				//System.out.println(pq);
				Node tmp = pq.poll();
				if(tmp.r==tmp.c&&tmp.r==N-1) {
					TC++;
					System.out.println("Problem "+TC+": "+tmp.value);
					break;
					
				}
				
				for(int i = 0 ; i < 4;i++) {
					int target_r = tmp.r+rotate_r[i];
					int target_c = tmp.c+rotate_c[i];
					
					if(check(target_r,target_c)&&!visit[target_r][target_c]) { //테이블에 넘어가지 않는 유요한 위치이며
						int tmp_check_price = (price[tmp.r][tmp.c]+table[target_r][target_c]);//해당 테이블로 넘어갔을 때 값
						//System.out.println(tmp_check_price + " " + price[target_r][target_c]);
						if(tmp_check_price<price[target_r][target_c]) {
							price[target_r][target_c] = tmp_check_price;
							pq.add(new Node(target_r,target_c,tmp_check_price));
							visit[target_r][target_c] = true;
						}
					}
				}

			}

		}

	}

	static boolean check(int r, int c) {
		if (r < 0 || r >= table.length || c < 0 || c >= table[0].length) {
			return false;
		}
		return true;
	}

}

class Node {
	int r;
	int c;
	int value;

	public Node(int r, int c,int value) {
		this.r = r;
		this.c = c;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [r=" + r + ", c=" + c + ", value=" + value + "]";
	}

}