import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
        System.out.println(Integer.parseInt(st.nextToken())-Integer.parseInt(st.nextToken()));
	}
}