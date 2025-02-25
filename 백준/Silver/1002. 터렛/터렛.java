import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		public static void main(String[] args) throws Exception {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st;

            int T = Integer.parseInt(bf.readLine());
            for(int t=0;t<T;t++){
                st = new StringTokenizer(bf.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int r1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                double gap = Math.pow(x2-x1,2)+Math.pow(y2-y1,2); //두점사이 거리
                if(x1==x2&&y1==y2&&r1==r2){
                    sb.append("-1");
                }else if((gap<Math.pow(r2-r1,2))||(gap>Math.pow(r2+r1,2))){
                    sb.append("0");
                }else if((gap==Math.pow(r2-r1,2))||(gap==Math.pow(r2+r1,2))){
                    sb.append("1");
                }else{
                    sb.append("2");
                }
                sb.append("\n");
            }
            System.out.println(sb);
	}
}
