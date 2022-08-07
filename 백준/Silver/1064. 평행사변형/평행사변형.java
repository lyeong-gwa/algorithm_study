import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		double[] len = new double[3];
		double min=Double.MAX_VALUE,max =Double.MIN_VALUE;
		len[0] = Math.sqrt(Math.pow(input[0] - input[2], 2) + Math.pow(input[1] - input[3], 2));
		len[1] = Math.sqrt(Math.pow(input[2] - input[4], 2) + Math.pow(input[3] - input[5], 2));
		len[2] = Math.sqrt(Math.pow(input[4] - input[0], 2) + Math.pow(input[5] - input[1], 2));
		
		for(int i = 0; i< 3;i++) {
			min = min>(len[i]+len[(i+1)%3])?len[i]+len[(i+1)%3]:min;
			max = max<(len[i]+len[(i+1)%3])?len[i]+len[(i+1)%3]:max;
		}
		if((input[3]-input[1])*(input[4]-input[2]) ==(input[5]-input[3])*(input[2]-input[0])){
			max=0;
			min=0.5;
		}
		
		System.out.println(2*(max-min));
	}
}