import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int SIZE = Integer.parseInt(bf.readLine());
		int[] table = new int[SIZE];
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> stack_idx = new Stack<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			table[i] = Integer.parseInt(st.nextToken());
		}
		
		

		for (int i = 0; i < table.length; i++) {
			while(!stack.isEmpty()&&stack.peek()<table[i]) {
				stack.pop();
				stack_idx.pop();
			}
			
			if(stack_idx.isEmpty()) {
				sb.append(0+" ");
			}else {
				sb.append(stack_idx.peek()+ " ");
			}
			
			stack.add(table[i]);
			stack_idx.add(i+1);

			
		}
		System.out.println(sb);

	}
}
