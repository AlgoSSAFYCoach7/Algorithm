class Solution {
    static int[] dr = {1,0,-1};
    static int[] dc = {0,1,-1};
    static int[][] map;
    static int N, max;
    public int[] solution(int n) {
        N = n;
        map = new int[N][];
        for(int i = 1; i <= N; i++){
            map[i-1] = new int[i];
        }
        max = N * (N+1) /2;
        int r = 0;
        int c = 0;
        int dir = 0;
        for(int i = 1; i <= max; i++){
            if( r + dr[dir] >= N || c + dc[dir]>= N ||map[r + dr[dir]][c + dc[dir]] != 0)
                dir = (dir + 1) % 3;
            map[r][c] = i;
            r += dr[dir];
            c += dc[dir];
        }
        int[] answer = new int[max];
        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < i+1; j++){
                answer[idx++] = map[i][j];
            }
        }
        
        return answer;
    }
}