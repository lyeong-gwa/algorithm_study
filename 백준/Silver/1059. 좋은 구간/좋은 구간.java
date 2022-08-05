import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int SIZE = Integer.parseInt(bf.readLine());
		int[] table = new int[SIZE+1];

		int answer = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			table[i] = Integer.parseInt(st.nextToken());
		}
		table[SIZE]=0;
		int N = Integer.parseInt(bf.readLine());
		Arrays.sort(table);
		int A = 1, B = table[0];
		for (int i = 0; i < table.length - 1; i++) {
			if (table[i] < N) {
				A = table[i];
				B = table[i + 1];
			}
			if (table[i] >= N) {
				break;
			}
		}

		if (A == N || B == N) {
			answer = 0;
		} else {
			answer = (N-A-1)+(B-N-1)+(N-A-1)*(B-N-1);
		}
		
		System.out.println(answer);
	}

}