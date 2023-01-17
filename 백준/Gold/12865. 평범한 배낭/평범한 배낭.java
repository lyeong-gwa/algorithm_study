import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());// 갯수
		int K = Integer.parseInt(st.nextToken());// 버티는 무게

		int[] bag = new int[K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());// 무게
			int V = Integer.parseInt(st.nextToken());// 가치

			for (int j = K; j - W >=0 ; j--) {//무게를 한계까지 들지 않도록 제한
				bag[j] = Math.max(bag[j], bag[j-W]+V);
			}
			//System.out.println(Arrays.toString(bag));
		}
		System.out.println(bag[K]);

	}
}