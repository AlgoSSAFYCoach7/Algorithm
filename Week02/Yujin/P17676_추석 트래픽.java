import java.util.*;
class Solution {
    static String[] infos;
    static ArrayList<Time> a;
    static int len;
    public int solution(String[] lines) {
        infos = lines;
        len = infos.length;
        a = new ArrayList<>();
        
        for(int i = 0; i < len; i++){
            String[] info = infos[i].split(" |-|\\.|s|:");
            int ms = transMs(info[2], info[3], info[4], info[5], info[6]);
            // a.add(new Time(ms , i));
            if(info.length == 9){
                a.add(new Time(ms - addMs(info[7], info[8]) + 1, ms, i));
            }else{
                a.add(new Time(ms - addMs(info[7]) + 1, ms, i));
            }
        }
        
        
        int answer = 0;
        
        for(int i = 0; i < len; i++){
            answer = Math.max(cntTime(i), answer);
        }

        return answer;
    }
    
   static int cntTime(int i){
       int cnt1 = 0;
       int cnt2 = 0;
       int startTime = a.get(i).ms;
       int startTimeEnd = startTime + 999;
       int endTime = a.get(i).ems;
       int endTimeEnd = endTime + 999;
       
       for(int j = 0; j < len; j++){
           if(a.get(j).ms >= startTime && a.get(j).ms <= startTimeEnd || a.get(j).ems >= startTime && a.get(j).ems <= startTimeEnd || a.get(j).ms <= startTime && a.get(j).ems >= startTimeEnd)
               cnt1++;
           if(a.get(j).ms >= endTime && a.get(j).ms <= endTimeEnd || a.get(j).ems >= endTime && a.get(j).ems <= endTimeEnd || a.get(j).ms <= endTime && a.get(j).ms >= endTimeEnd)
               cnt2++;
       }

       
       return Math.max(cnt1, cnt2);
   }
    
    static int transMs(String date, String h, String m, String s, String ms){
        return 
            ((( Integer.parseInt(date) * 24 + Integer.parseInt(h) ) * 60
            + Integer.parseInt(m)) *60 + Integer.parseInt(s)) * 1000 + Integer.parseInt(ms);
    }
    
    static int addMs(String s){
        return Integer.parseInt(s) * 1000 ;
    }
    
    static int addMs(String s, String ms){
        return Integer.parseInt(s) * 1000 + Integer.parseInt(ms) ;
    }
    
    static class Time implements Comparable<Time> {
        int ms, idx, ems;
        public Time(int ms, int ms2, int idx){
            this.ms = ms;
            this.idx = idx;
            this.ems = ms2;
        }
        @Override
        public int compareTo(Time o){
            return Integer.compare(this.ms, o.ms);
        }
    }
    
}