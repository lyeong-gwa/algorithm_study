import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		int M, A;
		USER user_A;
		USER user_B;
		int answer;

		T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(bf.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			user_A = new USER(1, 1, new int[M + 1]);
			user_B = new USER(10, 10, new int[M + 1]);
			st = new StringTokenizer(bf.readLine());
			List<AP> AP_list = new ArrayList<>();
			for (int i = 1; i < M + 1; i++) {
				user_A.move[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int i = 1; i < M + 1; i++) {
				user_B.move[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				AP_list.add(new AP(x, y, c, p));
			}

			Collections.sort(AP_list, (e1, e2) -> {
				return e2.p - e1.p;
			});

			for (int i = 0; i <= M; i++) {
				user_A.change_move(i);
				user_B.change_move(i);

				List<AP> A_inner = new ArrayList<>();
				List<AP> B_inner = new ArrayList<>();

				for (AP tmp_ap : AP_list) {
					if (tmp_ap.check(user_A)) {
						A_inner.add(tmp_ap);
					}
					if (tmp_ap.check(user_B)) {
						B_inner.add(tmp_ap);
					}
				}

				
				if(A_inner.size()!=0 &&B_inner.size()!=0) { //둘다 어딘가 속해있을 경우
					if(A_inner.get(0)==B_inner.get(0)) {// 우선순위 높은 ap가 겹치면 한명 포기하는게 좋다.
						A_inner.add(new AP(0,0,0,0));
						B_inner.add(new AP(0,0,0,0));
						answer+= A_inner.get(0).p;
						answer+= A_inner.get(1).p > B_inner.get(1).p? A_inner.get(1).p : B_inner.get(1).p;
					}
					else {
						answer+= A_inner.get(0).p;
						answer+= B_inner.get(0).p;
						
					}
					
				}else { //둘중 한개만 어딘가 속하거나 아무도 속하지 않은 경우
					if(A_inner.size()!=0) {
						answer+=A_inner.get(0).p;
					}
					if(B_inner.size()!=0) {
						answer+=B_inner.get(0).p;
					}
					
				}
				
			}
			sb.append("#"+tc+" "+answer+"\n");

		}
		System.out.println(sb);
	}
}

class USER {
	int x;
	int y;
	int[] move;

	public USER(int x, int y, int[] move) {
		this.x = x;
		this.y = y;
		this.move = move;
	}

	public void change_move(int idx) {
		if (move[idx] == 1) {
			this.y--;
		} else if (move[idx] == 2) {
			this.x++;
		} else if (move[idx] == 3) {
			this.y++;
		} else if (move[idx] == 4) {
			this.x--;
		}
	}
}

class AP {
	int x;
	int y;
	int c;
	int p;

	public AP(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}

	public boolean check(USER user) {
		if (Math.abs(x - user.x) + Math.abs(y - user.y) <= c)
			return true;
		return false;
	}
}