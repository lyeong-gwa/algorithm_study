import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main { // 키순서 메모이제이션 버전
	static int N, M;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] adjMatrix = new int[N + 1][N + 1]; // 0행~ 0열~ 은 메모이제이션에 사용

		for (int i = 1; i <= N; i++) {
			adjMatrix[i][0] = -1; // 아직 안끝난거
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
		}
		int answer = 0;
		for (int i = 1; i <= N; i++) { // 탐색시작점으로 반복
			if (adjMatrix[i][0] == -1) {
				dfs(i, adjMatrix);
				
			}
		}

		// 자신보다 작은 것은 큰 것의 역순으로 파악 가능
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				adjMatrix[0][k] += adjMatrix[i][k];
			}
		}
		for (int k = 1; k <= N; k++) {
			if (adjMatrix[k][0] + adjMatrix[0][k] == N - 1)
				answer++;
		}
		System.out.println(answer);

//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}

	}

	static void dfs(int cur, int[][] adjMatrix) {
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[cur][i] == 1) {
				
				if (adjMatrix[i][0] == -1)
					dfs(i, adjMatrix);

				if (adjMatrix[i][0] > 0) {// i보다 큰 정점 존재 -> cur<i<j를 만족하는 j가 존재한다 cur<j상태로 메모하는 것이다
					for (int j = 1; j <= N; j++) {
						if (adjMatrix[i][j] == 1) {
							adjMatrix[cur][j] = 1;
						}
					}

				}
			}
		}

		int cnt = 0;
		for (int k = 1; k <= N; k++) {
			cnt += adjMatrix[cur][k];
		}
		adjMatrix[cur][0] = cnt;

	}

}