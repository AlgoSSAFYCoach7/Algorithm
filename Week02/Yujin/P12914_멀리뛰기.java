import java.util.Arrays;
class Solution {
    public long solution(int n) {
        int N = n + 1;
        long[] dp = new long[N];
        
        dp[0] = 1;
        
        for(int i = 0; i < N; i++){
            if(i + 2 < N){
                dp[i + 2] += dp[i];
                dp[i + 2] %= 1234567;
            }
            if(i + 1 < N){
                dp[i + 1] += dp[i];
                dp[i + 1] %= 1234567;      
            }
        }


        return dp[n];
    }
}