import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static boolean[][] table;
    static boolean[] visit;
    static int answer = -1;
    static int user1;
    static int user2;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int user_cnt = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        user1 = Integer.parseInt(st.nextToken());
        user2 = Integer.parseInt(st.nextToken());
        int input_cnt = Integer.parseInt(bf.readLine());
        
        table = new boolean[user_cnt+1][user_cnt+1];
        visit = new boolean[user_cnt+1];

        for (int i = 0; i < input_cnt; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            table[a][b] = true;
            table[b][a] = true;
        }
        dfs(user1,0);
        System.out.println(answer);
    }
    static void dfs(int target,int depth){
        visit[target] = true;
        if(target==user2){
            answer = depth;
            return;
        }else{
            for(int i = 1 ; i < table.length;i++){
                if(table[target][i]&&!visit[i]){
                    dfs(i,depth+1);
                }
            }
        }
    }
}