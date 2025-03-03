import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static boolean[][] table;
    static boolean[] visit_list;
    static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
        int p_cnt = Integer.parseInt(st.nextToken());
        int e_cnt = Integer.parseInt(st.nextToken());
        int start_p = Integer.parseInt(st.nextToken());

        table = new boolean[p_cnt+1][p_cnt+1];
        for(int i = 0 ; i < e_cnt;i++){
            st = new StringTokenizer(bf.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            table[p1][p2] = true;
            table[p2][p1] = true;
        }
        visit_list = new boolean[p_cnt+1];
        DFS(start_p);
        System.out.println(sb.toString());

        visit_list = new boolean[p_cnt+1];
        sb.setLength(0);

        BFS(start_p);
        System.out.println(sb.toString());
	}
    static void BFS(int start_point){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start_point);

        while(!queue.isEmpty()){
            int target = queue.poll();
            if(!visit_list[target]){
                visit_list[target] = true;
                sb.append(target+" ");
            }
            
            for(int i = 1 ; i < visit_list.length ; i++){
                if(!visit_list[i]&&table[target][i]){
                    queue.add(i);
                }
            }
        }
    }
    static void DFS(int point){
        sb.append(point+" ");
        visit_list[point] = true;
        for(int i = 1 ; i < visit_list.length ; i++ ){
            if(!visit_list[i]&&table[point][i]){
                DFS(i);
            }
        }
    }



}