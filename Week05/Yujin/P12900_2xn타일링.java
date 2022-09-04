import java.util.*;
class Solution {
    static int N;
    static long[] cnt;
    public int solution(int n) {
        N = n;
        cnt = new long[N+1];
        cnt[1] = 1;
        cnt[2] = 2;
        for(int i = 3; i <= N; i++){
            cnt[i] = (cnt[i-1] + cnt[i-2]) % 1000000007L;
        }

        return (int)cnt[N];
    }
    
    
}