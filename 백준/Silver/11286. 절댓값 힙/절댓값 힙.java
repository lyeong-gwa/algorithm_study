import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> Q = new PriorityQueue<>((e1, e2) -> {
			if (Math.abs(e1) == Math.abs(e2)) {
				if (e1 > e2)
					return 1;
				else
					return -1;
			} else
				return Math.abs(e1) - Math.abs(e2);
		});

		int T = Integer.parseInt(bf.readLine());
		for (int i = 0; i < T; i++) {
			int tmp = Integer.parseInt(bf.readLine());

			if (tmp != 0) {
				Q.add(tmp);
			} else if (Q.size() > 0) {
				sb.append(Q.poll() + "\n");
			} else {
				sb.append(0 + "\n");
			}
		}
		System.out.println(sb);

	}

}