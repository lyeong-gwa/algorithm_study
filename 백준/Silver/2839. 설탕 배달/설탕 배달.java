import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{//2839
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		
		int cnt_5 = N/5;
		int cnt_3 = 0;
		
		N = N-cnt_5*5;
		
		while(N!=0) {
			if(N<3) {// 부족하다 5kg 찢는다.
				if(cnt_5<=0) { //5kg없다.
					break;//불가능하다.
				}else {
					cnt_5--;
					N+=5;
				}
			}else {
				N-=3;
				cnt_3+=1;
						
			}
		}
		if(N!=0) {
			System.out.println(-1);
		}else {
			System.out.println(cnt_5+cnt_3);
		}
		
	}
}