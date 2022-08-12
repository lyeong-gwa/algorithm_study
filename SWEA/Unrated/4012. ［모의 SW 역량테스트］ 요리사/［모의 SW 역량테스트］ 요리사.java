import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {// 4012

	static int[][] table;

	static int[] numbers;
	static int[] input;
	static int N;

	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T;
		T = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(bf.readLine());
			input = new int[N];
			table = new int[N][N];
			numbers = new int[N / 2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
				input[i] = i;

			}

			combi(0, 0);
			sb.append("#"+tc+" "+min+"\n");
		}

		System.out.println(sb);

	}

	public static void combi(int cnt, int start) {
		if (cnt == N / 2) {
			List<Integer> tmp_numbers = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				boolean check = true;
				for (int j = 0; j < numbers.length; j++) {
					if (numbers[j] == i) {
						check = false;
						break;
					}

				}
				if (check) {
					tmp_numbers.add(i);
				}
			}
			int num1 = 0;
			int num2 = 0;
			for (int i = 0; i < numbers.length - 1; i++) {
				for (int j = i + 1; j < numbers.length; j++) {
					int x = numbers[i];
					int y = numbers[j];
					num1 += table[x][y] + table[y][x];
				}
			}

			for (int i = 0; i < tmp_numbers.size() - 1; i++) {
				for (int j = i + 1; j < tmp_numbers.size(); j++) {
					int x = tmp_numbers.get(i);
					int y = tmp_numbers.get(j);
					num2 += table[x][y] + table[y][x];
				}
			}
			int answer = Math.abs(num1 - num2);
			min = answer < min ? answer : min;
			

			return;
		}
		for (int i = start; i < N; i++) {
			numbers[cnt] = input[i];
			combi(cnt + 1, i + 1);
		}
	}

}