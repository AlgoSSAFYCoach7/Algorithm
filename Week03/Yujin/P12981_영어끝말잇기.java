import java.util.*;
class Solution {
    static HashSet<String> map;
    static int N, len;
    static String[] wordss;
    public int[] solution(int n, String[] words) {
        map = new HashSet<>();
        wordss = words;
        N = n;
        len = words.length;
        int idx = 1;
        map.add(wordss[0]);
        while(idx < len){
            // System.out.println(wordss[idx]);
            String ex = wordss[idx-1];
            if(map.contains(wordss[idx]) || wordss[idx].length() == 1 || ex.charAt(ex.length() - 1) != wordss[idx].charAt(0))
                break;
            map.add(wordss[idx]);
            idx++;
        }
        // System.out.println(idx);
        // System.out.println(idx / N);
        int[] ans = new int[2];
        if(idx < len){
            ans[0] = idxPrint((idx + 1)%N);
            ans[1] = (idx+1) / N + ((idx+1) % N == 0 ? 0 : 1);
        }
        // System.out.println(Arrays.toString(ans));
        return ans;
    }
    
    static int idxPrint(int idx){
        if (idx == 0)
            return N;
        else
            return idx;
    }
}