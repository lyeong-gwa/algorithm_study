import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 16236 아기상어
	static int[][] map;
	static int N;
	static int size;
	static int eat;
	static PriorityQueue<Point> queue;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		queue = new PriorityQueue<Point>((e1,e2)->{
			if(e1.dist == e2.dist) {
				if(e1.r==e2.r) {
					return e1.c-e2.c;
				}
				return e1.r-e2.r;
			}
			else {
				return e1.dist-e2.dist;
			}
		});
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		answer = 0;
		size = 2;
		eat = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					queue.add(new Point(i, j, 0));
					map[i][j] = 0;
				}
			}
		}
		int search_value;
		while((search())!=0) {
		}
		System.out.println(answer);
	}
	public static int search() {
		boolean[][] visit = new boolean[N][N];
		visit[queue.peek().r][queue.peek().c] = true;
		while (!queue.isEmpty()) {
			
			int cycle = queue.size();
			
			for (int i = 0; i < cycle; i++) {
				Point p = queue.poll();
				if (map[p.r][p.c] != 0 && size > map[p.r][p.c]) {// 먹이를 찾았다.
					queue = new PriorityQueue<Point>((e1,e2)->{
						if(e1.dist == e2.dist) {
							if(e1.r==e2.r) {
								return e1.c-e2.c;
							}
							return e1.r-e2.r;
						}
						else {
							return e1.dist-e2.dist;
						}
					});
					queue.add(p);
					eat++;
					if(eat ==size) {
						eat =0;
						size++;
					}
					map[p.r][p.c] = 0;
					answer = p.dist;
					return p.dist;
				}

				if (check(p.r - 1, p.c) && !visit[p.r - 1][p.c]) {
					queue.add(new Point(p.r - 1, p.c, p.dist + 1));
					visit[p.r - 1][p.c] = true;
				}
				if (check(p.r, p.c - 1) && !visit[p.r][p.c - 1]) {
					queue.add(new Point(p.r, p.c - 1, p.dist + 1));
					visit[p.r][p.c - 1] = true;
				}
				if (check(p.r, p.c + 1) && !visit[p.r][p.c + 1]) {
					queue.add(new Point(p.r, p.c + 1, p.dist + 1));
					visit[p.r][p.c + 1] = true;
				}
				if (check(p.r + 1, p.c) && !visit[p.r + 1][p.c]) {
					queue.add(new Point(p.r + 1, p.c, p.dist + 1));
					visit[p.r + 1][p.c] = true;
				}

			}


			
		}
		return 0;
	}
	

	public static boolean check(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] > size) {
			return false;
		}

		return true;
	}

}

class Point {
	int r;
	int c;
	int dist;

	public Point(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + ", dist=" + dist + "]";
	}

}