import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        int input = Integer.parseInt(bf.readLine());
		int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < input; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            min = Math.min(min,tmp);
            max = Math.max(max,tmp);
		}
        System.out.println(min*max);
		

	}
}