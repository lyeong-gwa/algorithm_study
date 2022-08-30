import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		while(true) {
			st = new StringTokenizer(bf.readLine());
			String name = st.nextToken();
			int age = Integer.parseInt(st.nextToken());
			int kg = Integer.parseInt(st.nextToken());
			
			if(name.equals("#")&& age==kg && age==0) {
				break;
			}
			
			String title = (age>17||kg>=80)?"Senior":"Junior";
			System.out.println(name + " " + title);
			
		}
		

	}
}
