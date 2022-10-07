import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static Magnetic[] magnetic;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		magnetic = new Magnetic[4];
		int tmp_score = 1;
		for (int i = 0; i < 4; i++) {
			magnetic[i] = new Magnetic(tmp_score);
			tmp_score *= 2;
		}

		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int total = 0;
			int K = Integer.parseInt(bf.readLine());
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 8; j++) {
					magnetic[i].data[j] = Integer.parseInt(st.nextToken());
				}
				magnetic[i].check = false;
				magnetic[i].idx = 0;

			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine());
				int target = Integer.parseInt(st.nextToken()) - 1;
				int rotate = Integer.parseInt(st.nextToken());

				move_magnetic(target, rotate);

				for (Magnetic tmp : magnetic) {
					tmp.check = false;
				}

			}
			for (int k = 0; k < 4; k++) {
				total += magnetic[k].getScore();
			}
			sb.append("#"+tc+" "+total+"\n");

		}
		System.out.println(sb);

	}

	// 1 1 1번 자석 시계방향
	static void move_magnetic(int target, int rotate) {
		if (magnetic[target].check) {
			return;
		}
		magnetic[target].check = true; // 일단 체크했다고 표시남기기

		if (target - 1 >= 0) {//
			if (magnetic[target].data[(magnetic[target].idx + 6)
					% 8] != magnetic[target - 1].data[(magnetic[target - 1].idx + 2) % 8]) {
				move_magnetic(target - 1, -rotate);
			}
		}
		if (target + 1 < 4) {
			if (magnetic[target].data[(magnetic[target].idx + 2)
					% 8] != magnetic[target + 1].data[(magnetic[target + 1].idx + 6) % 8]) {
				move_magnetic(target + 1, -rotate);
			}
		}
		magnetic[target].move(rotate);// 회전
	}

	static class Magnetic {
		int[] data;
		int idx;
		boolean check;
		int score;

		public Magnetic(int score) {
			data = new int[8];
			idx = 0;
			check = false;
			this.score = score;
		}

		public void move(int rotate) {
			idx = (idx - rotate + 8) % 8;
		}

		public int getScore() {
			return score * data[idx];
		}
	}
}
