import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int max = 0;
			sb.append("#" + tc + " ");
			int size = Integer.parseInt(bf.readLine());
			st = new StringTokenizer(bf.readLine());

			int[] arr = new int[size];
			int[] LIS = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < size; i++) {
				LIS[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j] + 1;
					}
				}
				max = LIS[i]>max?LIS[i]:max;
			}

			sb.append(max + "\n");
		}
		System.out.println(sb);
	}

}
