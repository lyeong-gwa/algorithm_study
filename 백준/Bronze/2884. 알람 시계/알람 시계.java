import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws  Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = bf.readLine().split(" ");
		int h,m;
		
		h = Integer.parseInt(input[0])-1;
		m = Integer.parseInt(input[1])+60;
		
		m -=45;
		if(m>=60) {
			h++;
			m-=60;
		}
		if(h<0) {
			h=23;
		}
		System.out.println(h + " " + m);
	}
	
	
}