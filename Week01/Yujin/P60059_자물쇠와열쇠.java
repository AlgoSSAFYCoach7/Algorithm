class Solution {
    static int[][][] keys;
    static int[][] okey, olock;
    static int[][] map;
    static int N, M, L;
    public boolean solution(int[][] key, int[][] lock) {
        okey = key;
        olock = lock;
        N = key.length;
        M = lock.length;
        L = M - 2 + N * 2;
        keys = new int[4][N][N];
        
        setKey();
        
        return simul();
    }
    
    static void setKey(){
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                keys[0][r][c] = okey[r][c];
            }
        }
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                keys[1][r][c] = okey[N - 1 - c][r];
            }
        }
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                keys[2][r][c] = okey[N - 1 - r][N - 1 - c];
            }
        }
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                keys[3][r][c] = okey[c][N - 1 - r];
            }
        }
    }
    
    static void setLock(int[][] map){
        for(int r = N-1;r < N+M-1; r++){
            for(int c = N-1;c<N+M-1;c++){
                for(int i = 0; i < 4; i++){
                    map[r][c] = olock[r-N+1][c-N+1];
                }
            }
        }
    }
    
    static boolean simul(){
        for(int r = 0;r < N+M-1; r++){
            for(int c = 0;c<N+M-1;c++){
                for(int i = 0; i < 4; i++){
                    map = new int[L][L];
                    setLock(map);
                    
                    
                    if(!chkClack(map, r, c, i))
                        continue;
                    
                    //viewMap(map);
                    //System.out.println();
                    
                    if(chkUnlock(map))
                        return true;
                }
            }
        }
        return false;
    }
    
    static boolean chkClack(int[][] map, int sr, int sc, int idx){
        for(int r = sr;r < sr+N; r++){
            for(int c = sc;c<sc+N;c++){
                map[r][c]+=keys[idx][r-sr][c-sc];
                if(map[r][c] == 2)
                    return false;
            }
        }
        return true;
    }
    
    static boolean chkUnlock(int[][] map){
        for(int r = N-1;r < M+N-1; r++){
            for(int c = N-1;c<M+N-1;c++){
                if(map[r][c] == 0)
                    return false;
            }
        }
        return true;
    }
    
    static void viewMap(int[][] map){
        for(int r = 0 ; r<L;r++){
            for(int c = 0 ; c<L;c++){
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }
    
}