import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {// 1922
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		int[][] Edge = new int[N][N];
		boolean[] visit = new boolean[N];
		int[] minEdge = new int[N];
		int answer = 0;
		for (int i = 0; i < N; i++) {
			Edge[i] = new int[N];
			Arrays.fill(Edge[i], 100000);
			minEdge[i] = 100000;
		}

		// 간선 정보 저장 from == to 인 경우도 있다고 하기에 이런 건 0으로 만들어야 된다.
		// 계산편의를 위해 1 -> 0으로 생각
		// 정보가 없는 간선은 선택되지 않기 위해 가장 큰 값인 1만보다 더 큰 숫자를 채워 넣어두었다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int price = Integer.parseInt(st.nextToken());

			if (from == to) {
				Edge[to][to] = 0;
				continue;
			}
			Edge[from][to] = price;
			Edge[to][from] = price;

		}

        //가장 작은 것으로 세팅하면 최초로 선택된다.
		minEdge[0] = 0;// 최초 선택 간선
		for (int i = 0; i < N; i++) {
			int min_value = Integer.MAX_VALUE;
			int min_idx = 0;

			for (int j = 0; j < N; j++) {
				if (!visit[j] && min_value > minEdge[j]) {
					min_value = minEdge[j];
					min_idx = j;
				}
			}
			answer += min_value;
			visit[min_idx] = true;
			for (int j = 0; j < N; j++) {
				if(!visit[j] &&Edge[min_idx][j]<minEdge[j]) { //a==b에서 값도 존재하는데 필요없으므로 생략
					minEdge[j] = Edge[min_idx][j];
				}
			}

		}

		System.out.println(answer);

	}
}