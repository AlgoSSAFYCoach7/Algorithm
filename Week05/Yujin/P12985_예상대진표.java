class Solution
{
    public int solution(int n, int a, int b)
    {   int round = 0;
        while(!chkSame(a,b)){
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            round++;
        }
     return round + 1;
    }
    
    static boolean chkSame(int a, int b){
        return (a + 1) / 2 == (b + 1) / 2;
    }
}