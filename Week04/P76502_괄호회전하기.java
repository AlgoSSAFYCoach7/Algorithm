import java.util.*;
class Solution {
    static Stack<Character> stack;
    static int len;
    static String os;
    public int solution(String s) {
        os = s;
        len = s.length();
        int ans = 0;
        for(int i = 0; i < len; i++){
            ans += chk(i) ? 1 : 0;
        }
        
        return ans;
    }
    static boolean chk(int idx){
        stack = new Stack<>();
        char[] ss = (os.substring(idx,len) + os.substring(0,idx)).toCharArray();
        
        for(char now : ss){
            if(stack.isEmpty() || now != reverse(stack.peek()))
                stack.push(now);
            else
                stack.pop();
            // System.out.println(stack.isEmpty() ? "null" : stack.peek());
        }
        
        
        return stack.size() == 0 ? true : false;
    }
    
    static char reverse(char in){
        switch(in){
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
        }
        return 'x';
    }
    
   
}