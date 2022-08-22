import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int L;
	static int C;
	static char[] input;
	static char[] answer;
	static boolean[] select;
	static List<Character> moum_list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		moum_list = new ArrayList<Character>();
		moum_list.add('a');
		moum_list.add('e');
		moum_list.add('i');
		moum_list.add('o');
		moum_list.add('u');

		input = new char[C];
		answer = new char[L];
		select = new boolean[C];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < C; i++) {
			input[i] = (char) st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		combi(0, 0, 0, 0);

	}

	public static void combi(int cnt, int start, int c, int v) { // c자음 v모음
		if (cnt == L) {
			if (c >= 2 && v >= 1) {
				System.out.println(new String(answer));
			}
			return;
		}
		for (int i = start; i < C; i++) {
			answer[cnt] = input[i];
			if (moum_list.contains(answer[cnt]))
				combi(cnt + 1, i + 1, c, v + 1);
			else {
				combi(cnt + 1, i + 1, c + 1, v);
			}
		}

	}

}