import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static boolean[][] table;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt_node = Integer.parseInt(bf.readLine());
        int cnt_edge = Integer.parseInt(bf.readLine());
        table = new boolean[cnt_node + 1][cnt_node + 1];
        visit = new boolean[cnt_node + 1];

        for (int i = 0; i < cnt_edge; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            table[a][b] = true;
            table[b][a] = true;
        }
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;
        queue.add(1);
        while (!queue.isEmpty()) {
            int target = queue.poll();
            if (!visit[target]) {
                visit[target] = true;
                answer += 1;
                for (int i = 1; i < visit.length; i++) {
                    if (table[target][i]) {
                        queue.add(i);
                    }
                }
            }
        }
        System.out.println(answer - 1);
    }
}