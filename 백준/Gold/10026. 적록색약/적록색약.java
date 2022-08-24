import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
	static char[][] map;
	static int[] rotate_r = {-1,0,1,0};
	static int[] rotate_c = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		
		char[][] original = new char[N][N];
		map = new char[N][N];
		
		for(int i = 0 ; i < N ; i ++) {
			original[i] = bf.readLine().toCharArray();
			for(int j =0;j<N;j++) {
				map[i][j] = original[i][j];
			}
		}
		
		int original_cnt = 0 ;
		int equal_RG_cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0; j < N ; j++) {
				if(map[i][j]!='E') {
					dfs(i,j,map[i][j]);
//					for(int k = 0 ; k < N ; k++) {
//						System.out.println(Arrays.toString(map[k]));
//					}
//					System.out.println();
					original_cnt++;
				}
			}
		}
		for(int i = 0 ; i < N ;i++) {
			for(int j = 0; j < N ; j++) {
				map[i][j] = original[i][j] == 'B' ? 'B':'R';
			}
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0; j < N ; j++) {
				if(map[i][j]!='E') {
					dfs(i,j,map[i][j]);
//					for(int k = 0 ; k < N ; k++) {
//						System.out.println(Arrays.toString(map[k]));
//					}
//					System.out.println();
					equal_RG_cnt++;
				}
			}
		}
		System.out.println(original_cnt + " " + equal_RG_cnt);
		
	}
	
	static void dfs(int r,int c,char color) {
		map[r][c] = 'E';
		for(int i = 0 ; i < 4; i++) {
			int target_r = r+rotate_r[i];
			int target_c = c+rotate_c[i];
			if(check(target_r,target_c)&&map[target_r][target_c] == color) {
				dfs(target_r,target_c,color);
			}
			
		}
		
	}
	
	
	public static boolean check(int r, int c) {
		if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) {
			return false;
		}

		return true;
	}
	
}
