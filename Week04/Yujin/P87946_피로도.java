class Solution {
    
    static boolean[] chk;    
    static int[][] dun;
    static int max, len;
    public int solution(int k, int[][] dungeons) {
        dun = dungeons;
        len = dun.length;
        chk = new boolean[len];
        max = 0;
        dfs(0,0,k);
        return max;
    }
    
    static void dfs(int dep, int cnt, int hp){
        if(dep == len || hp <= 0){
            max = Math.max(cnt, max);
            return;
        }
        int pos = 0;
        for(int i = 0; i < len ; i++){
            if(dun[i][0] <= hp && !chk[i] ){
                pos++;
                chk[i] = true;
                dfs(dep + 1, cnt + 1, hp - dun[i][1]);
                chk[i] = false;
            }
        }
        if(pos == 0)
            dfs(dep , cnt , -1);
        
    }
}