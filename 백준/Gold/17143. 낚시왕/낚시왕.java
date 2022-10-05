import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static Field[][] table;
	static ArrayList<Shark> shark_list;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer = 0;
		st = new StringTokenizer(bf.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		table = new Field[R][C];
		shark_list = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				table[i][j] = new Field();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark tmp = new Shark(r, c, s, d, z);
			shark_list.add(tmp);
			table[r][c].list.add(tmp);
		}

		for (int user = 0; user < C; user++) {
			answer += shark_catch(user);
			shark_move();

//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					String a = "" + 0;
//					if (table[i][j].list.size() > 0) {
//						a = "" + table[i][j].list.get(0).z;
//					}
//
//					System.out.print(a + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("--------------------------------");

		}
		System.out.println(answer);

	}

	static int shark_catch(int user) {
		for (int i = 0; i < table.length; i++) {
			if (table[i][user].list.size() == 1) {
				int val = table[i][user].list.get(0).z;
				table[i][user].list.get(0).remove=true;
				table[i][user].list.remove(0);
				
				remove_shark();
				return val;
			}
		}

		return 0;
	}

	static void shark_move() {
		for (Shark tmp : shark_list) {

			// 이동하기전 필드에서 자신을 삭제
			for (int i = 0; i < table[tmp.r][tmp.c].list.size(); i++) {
				if (table[tmp.r][tmp.c].list.get(i).z == tmp.z) {// 크기를 id로 사용가능
					table[tmp.r][tmp.c].list.remove(i);
					break;
				}
			}

			// 이동
			int next_r = tmp.r + tmp.s * tmp.d_r;
			int next_c = tmp.c + tmp.s * tmp.d_c;
			while (!isIn(next_r, next_c)) {// 벽과 충돌함 -> 재조율
				if (next_r < 0) {
					next_r *= -1;
				} else if (next_r >= table.length) {
					next_r = 2 * (table.length - 1) - next_r;
				} else if (next_c < 0) {
					next_c *= -1;
				} else { // next_c>=table.length
					next_c = 2 * (table[0].length - 1) - next_c;
				}
				tmp.d_r *= -1;
				tmp.d_c *= -1;

			}

			// 상어 위치변경
			tmp.r = next_r;
			tmp.c = next_c;
			table[next_r][next_c].list.add(tmp);

		}

		// 같은 장소에 있는 상어 작은거 삭제
		for (Shark tmp : shark_list) {
			if (table[tmp.r][tmp.c].list.size() > 1) {
				if (table[tmp.r][tmp.c].list.get(0).z > table[tmp.r][tmp.c].list.get(1).z) {
					table[tmp.r][tmp.c].list.get(1).remove = true;
					table[tmp.r][tmp.c].list.remove(1);

				} else {
					table[tmp.r][tmp.c].list.get(0).remove = true;
					table[tmp.r][tmp.c].list.remove(0);
				}
			}
		}

		// 상어 리스트에서 삭제
		remove_shark();
	}
	static void remove_shark() {
		for (int i = shark_list.size() - 1; i >= 0; i--) {
			if(shark_list.get(i).remove) {
				shark_list.remove(i);
			}
		}
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < table.length && c < table[0].length) {
			return true;
		}
		return false;
	}

	static class Field {
		ArrayList<Shark> list;

		public Field() {
			list = new ArrayList<>();
		}
	}

	static class Shark {
		int r;
		int c;
		int s;// 속력
		int d_r;
		int d_c;
		int z;// 크기
		boolean remove;

		public Shark(int r, int c, int s, int d, int z) {
			remove = false;
			this.r = r;
			this.c = c;
			this.s = s;
			this.z = z;
			if (d == 1) {
				d_r = -1;
				d_c = 0;
			} else if (d == 2) {
				d_r = 1;
				d_c = 0;
			} else if (d == 3) {
				d_r = 0;
				d_c = 1;
			} else {// d==4
				d_r = 0;
				d_c = -1;
			}

		}

	}

}