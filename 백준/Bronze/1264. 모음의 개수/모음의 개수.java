import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws  Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str;
		
		while(true) {
			str = bf.readLine().toCharArray();
			if(str.length==1 && str[0]=='#') {
				break;
			}
			int cnt = 0;
			for(char c:str) {
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U') {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
		
		
	}
	
	
}