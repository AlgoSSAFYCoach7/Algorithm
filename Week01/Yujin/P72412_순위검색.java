import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    static String[] i,q, qq;
    static Query nq;
    static int L, cnt;
    public int[] solution(String[] info, String[] query) {
        i = info;
        q = query;
        L = query.length;
       
        map = new HashMap<>();
        // info map에 key, val 정리해서 집어 넣기
        for(String tmp : i){
            String[] words = tmp.split(" ");
            String key = (words[0].equals("cpp") ? "1" : words[0].equals("java") ? "2" : "3") 
                + (words[1].equals("backend") ? "1" : "2") 
                + (words[2].equals("junior") ? "1" : "2")
                + (words[3].equals("chicken") ? "1" : "2");
            int val = Integer.parseInt(words[4]);
            
            if(map.containsKey(key))
                map.get(key).add(val);
            else{
                map.put(key, new ArrayList<>());
                map.get(key).add(val);
            }
        }
        
        // 정렬
        for(String key : map.keySet()){
            // Collections.sort(map.get(key), Collections.reverseOrder());
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[L];
        for(int l = 0; l < L; l++){
            cnt = 0;
            chkCnt(l);
            answer[l] = cnt;
        }
        return answer;
    }
    
    static void chkCnt(int idx){
        qq = q[idx].split(" ");
        // System.out.println(qq[0] + qq[2] + qq[4] + qq[6]);
        nq = new Query();
        if(qq[0].equals("-")){
            nq.info[0].add("1");
            nq.info[0].add("2");
            nq.info[0].add("3");
        }else if(qq[0].equals("cpp"))
            nq.info[0].add("1");
        else if(qq[0].equals("java"))
            nq.info[0].add("2");
        else
            nq.info[0].add("3");
        
        if(qq[2].equals("-")){
            nq.info[1].add("1");
            nq.info[1].add("2");
        }else if(qq[2].equals("backend"))
            nq.info[1].add("1");
        else
            nq.info[1].add("2");
        
        if(qq[4].equals("-")){
            nq.info[2].add("1");
            nq.info[2].add("2");
        }else if(qq[4].equals("junior"))
            nq.info[2].add("1");
        else
            nq.info[2].add("2");
        
        if(qq[6].equals("-")){
            nq.info[3].add("1");
            nq.info[3].add("2");
        }else if(qq[6].equals("chicken"))
            nq.info[3].add("1");
        else
            nq.info[3].add("2");
        
        nq.score = Integer.parseInt(qq[7]);
        
        // for(int i = 0; i < 4; i++){
        //     for(String sss: nq.info[i])
        //         System.out.print(sss);
        //     System.out.println();
        // }
        
        
        dfs(0, "");
    }
    
    static void dfs(int dep, String k){
        if(dep == 4){
            int vv = nq.score;
            if(map.containsKey(k)){
                // ArrayList<Integer> tmp = map.get(k);
                // System.out.println(k);

                // for(int tmpNum : tmp){
                //     if(tmpNum >= vv)
                //         cnt++;
                //     else
                //         break;
                // }
                
               cnt += biSearch(map.get(k), vv);

            }
            return;
        } 
        
        for(String infoKey : nq.info[dep]){
            dfs(dep+1, k + infoKey);
        }
    }
    
    static int biSearch(ArrayList<Integer> tmp, int goal){
        int s = 0;
        int e = tmp.size() - 1;
        
        while(s <= e){
            int mid = (s + e) / 2;
            if(tmp.get(mid) < goal){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        
        return tmp.size() - s;
        
    }
    
    static class Query{
        ArrayList<String>[] info;
        int score;
        public Query(){
            this.info = new ArrayList[4];
            this.info[0] = new ArrayList<>();
            this.info[1] = new ArrayList<>();
            this.info[2] = new ArrayList<>();
            this.info[3] = new ArrayList<>();
            this.score = 0;
        }
    }
}