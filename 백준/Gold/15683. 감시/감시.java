import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] table;
	static int answer;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		table = new int[N][M];
		answer = 100;
		List<CCTV> p_list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				if (table[i][j] != 0 && table[i][j] != 6) {
					p_list.add(new CCTV(i, j, table[i][j]));
				}
			}
		}
		for(CCTV cctv:p_list) {
			cctv.init_udrl();
		}
		
		combi_search(p_list,0,new ArrayList<Integer>());
		System.out.println(answer);
	}
	public static void combi_search(List<CCTV> cctv,int depth, List<Integer> idx_list) {
		
		if(cctv.size()==idx_list.size()) {
			List<Point> tmp_p_list = new ArrayList<>();
			int[][] tmp_table = new int[N][M];
			for(int i = 0 ; i < idx_list.size();i++) {
				tmp_p_list.addAll(cctv.get(i).combi.get(idx_list.get(i)));
			}
			for(Point t_point: tmp_p_list) {
				tmp_table[t_point.x][t_point.y] = 1;
			}
			int cnt = N*M;
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < M ; j++) {
					if(table[i][j]==6 || tmp_table[i][j]==1) {
						cnt--;
					}
				}
			}
			answer = cnt<answer?cnt:answer;
			
			
		} else{
			for(int i = 0 ; i < cctv.get(depth).combi.size();i++) {
				idx_list.add(i);
				combi_search(cctv,depth+1,idx_list);
				idx_list.remove(depth);
			}
		}
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class CCTV {
	Point point;
	int num;

	List<Point> up;
	List<Point> down;
	List<Point> right;
	List<Point> left;
	List<List<Point>> combi;
	public CCTV(int x, int y, int num) {
		point = new Point(x, y);
		this.num = num;
	}
	public void init_udrl() {
		up = linecheck(-1, 0);
		down = linecheck(1, 0);
		right = linecheck(0, 1);
		left = linecheck(0, -1);
		combi = new ArrayList<>();
		make_combi();
	}

	public void make_combi() {
		if(num == 5) {
			List<Point> all_udrl = new ArrayList<>();
			all_udrl.addAll(right);
			all_udrl.addAll(down);
			all_udrl.addAll(left);
			all_udrl.addAll(up);
			combi.add(all_udrl);
		}else if(num == 4) {
			List<Point> no_u = new ArrayList<>();
			List<Point> no_d = new ArrayList<>();
			List<Point> no_r = new ArrayList<>();
			List<Point> no_l = new ArrayList<>();
			
			no_u.addAll(right);
			no_u.addAll(down);
			no_u.addAll(left);

			no_d.addAll(up);
			no_d.addAll(right);
			no_d.addAll(left);
			
			no_r.addAll(up);
			no_r.addAll(down);
			no_r.addAll(left);
			
			no_l.addAll(up);
			no_l.addAll(right);
			no_l.addAll(down);
			
			combi.add(no_u);
			combi.add(no_d);
			combi.add(no_r);
			combi.add(no_l);
		}else if(num == 3) {
			List<Point> u_r = new ArrayList<>();
			List<Point> r_d = new ArrayList<>();
			List<Point> d_l = new ArrayList<>();
			List<Point> l_u = new ArrayList<>();
			
			u_r.addAll(up);
			u_r.addAll(right);
			r_d.addAll(right);
			r_d.addAll(down);
			d_l.addAll(down);
			d_l.addAll(left);
			l_u.addAll(left);
			l_u.addAll(up);
			combi.add(u_r);
			combi.add(r_d);
			combi.add(d_l);
			combi.add(l_u);
			
			
		}else if(num ==2) {
			up.addAll(down);
			right.addAll(left);
			combi.add(up);
			combi.add(right);
			
		}else {
			combi.add(up);
			combi.add(down);
			combi.add(left);
			combi.add(right);
		}
	}
	
	public List<Point> linecheck(int x, int y) {
		List<Point> tmp = new ArrayList<>();
		tmp.add(point);
		int cnt = 1;
		while(check_out(point.x+cnt*x,point.y+cnt*y)) {
			tmp.add(new Point(point.x+cnt*x,point.y+cnt*y));
			cnt++;
		}
			return tmp;
	}

	public boolean check_out(int x,int y) {
		if( 0<=x && x<Main.table.length && 0 <= y && y < Main.table[0].length && Main.table[x][y]!=6) {
			return true;
		}
		return false;
	}
}