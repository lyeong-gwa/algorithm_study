import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		boolean swi = true;
		int input = Integer.parseInt(bf.readLine());
		for (int i = 0; i < input; i++) {
			Q.add(i+1);
		}
		
		while(Q.size()!=1) {
			if(swi) {Q.remove();}
			else {Q.add(Q.poll());}
			swi=!swi;
		}
		
		System.out.println(Q.poll());

	}
}
