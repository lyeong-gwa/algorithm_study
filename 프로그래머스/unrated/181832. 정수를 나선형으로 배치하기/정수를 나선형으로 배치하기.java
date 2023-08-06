class Solution {
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    public int[][] solution(int n) {
        int idx = 0;
        int r = 0;
        int c = 0;
        int loop = n*n;
        int[][] answer = new int[n][n];
        
        
        for(int i = 1 ; i <= loop ; i++){
            answer[r][c] = i;
            if(!check(r+dr[idx],c+dc[idx],n,answer)){
                idx = (idx+1)%4;
            }
            r += dr[idx];
            c += dc[idx];
        }
        
        return answer;
    }
    static boolean check(int r,int c, int n, int[][] answer){
        if(r < 0 || c < 0 || r>= n || c>= n || answer[r][c]!=0){
            return false;
        }
        return true;
    }
    
}