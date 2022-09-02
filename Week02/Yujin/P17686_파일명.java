import java.util.regex.*;
import java.util.*;
class Solution {
    static String[] fileNames;
    static int len;
    public String[] solution(String[] files) {
        fileNames = files;
        len = files.length;
        Pattern p = Pattern.compile("([a-zA-Z\\.\\-\\s]+)|([0-9]{0,5})");
        PriorityQueue<Line> pq = new PriorityQueue<>();
        for(int i = 0; i < len; i++){
            Matcher m = p.matcher(fileNames[i]);
            String h = "";
            String n = "";
            String t = "";
            int j = 0;
            while(m.find()){
                if(j == 0)
                    h += m.group();
                else if(j == 1)
                    n += m.group();
                else
                    t += m.group();
                j++;
            }
            // System.out.println("h : " + h +"/ n : " + n + "/ t : "+t +"/ idx : " + i);
            pq.add(new Line(h, n, t, i, fileNames[i]));
        }
        
        String[] answer = new String[len];
        for(int i = 0; i < len; i++){
            answer[i] = pq.poll().origin;
        }
        return answer;
    }
    
    static class Line implements Comparable<Line>{
        String head, tail, origin;
        int num, idx;
        
        public Line(String head, String num, String tail, int idx, String origin){
            this.head = head.toUpperCase();
            this.num = Integer.parseInt(num);
            this.tail = tail.toUpperCase();
            this.idx = idx;
            this.origin = origin;
        }
        
        @Override
        public int compareTo(Line l){
            if(this.head.equals(l.head)){
                if(this.num == l.num){
                    // if(this.tail.equals(l.tail)){
                        return Integer.compare(this.idx, l.idx);
                    // }else
                    //     return this.tail.compareTo(l.tail);
                }else
                    return Integer.compare(this.num, l.num);
                    
            }else
                return this.head.compareTo(l.head);
            
        }
    }
    
    
}