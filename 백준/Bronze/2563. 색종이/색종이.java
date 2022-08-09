import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] table = new boolean[100][100];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int init = 0;
		for(int tc = 0; tc < T;tc++) {
			
			String[] tmp = br.readLine().split(" ");
			int x = Integer.parseInt(tmp[0]);
			int y = Integer.parseInt(tmp[1]);
			for(int i = x ; i < x+10;i++) {
				for(int j = y; j < y+10;j++) {
					if(!table[i][j]) {
						init++;
						table[i][j]=true;
					}
				}
			}
		}
		System.out.println(init);
	}


}