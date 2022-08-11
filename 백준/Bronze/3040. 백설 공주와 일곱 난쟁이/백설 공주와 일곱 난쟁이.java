import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] dwarf = new int[9];
		boolean[] select = new boolean[9];
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			dwarf[i] = Integer.parseInt(bf.readLine());
			sum += dwarf[i];
		}
		sum-=100;
		for(int i = 0; i < 8;i++) {
			for(int j = i+1 ; j < 9; j++) {
				if(dwarf[i]+dwarf[j]==sum) {
					select[i] = true;
					select[j] = true;
				}
			}
		}
		for(int i = 0 ; i < select.length;i++) {
			if(select[i]==false) {
				System.out.println(dwarf[i]);
			}
		}

	}
}