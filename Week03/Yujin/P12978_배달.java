import java.util.*;
class Solution {
    static Node[] nodes;
    static int[][] infos;
    static int[] ans;
    static boolean[] chk;
    public int solution(int N, int[][] road, int K) {
        nodes = new Node[N+1];
        ans = new int[N+1];
        
        infos = road;
        for(int i = 0; i < N + 1; i ++){
            nodes[i] = new Node(i);
            ans[i] = Integer.MAX_VALUE;
        }
        
        for(int[] info : infos ){
            int s = info[0];
            int d = info[1];
            int dis = info[2];
            
            nodes[s].a.add(new Edge(dis, d));
            nodes[d].a.add(new Edge(dis, s));
        }
        
        // for(Node n : nodes ){
        //     System.out.println(n.a.toString());
        // }
        
        int now = 1; // 현재 탐색할 노드
        ans[now] = 0; // 초기 마을 위치 0으로 
        chk = new boolean[N+1]; // 방문 확인용
        
        for(int i = 1; i <= N; i++){
            now = 0;
            int nowDis = Integer.MAX_VALUE;
            for(int j = 1; j <= N; j++){
                if(!chk[j] && nowDis >= ans[j]){
                    now = j;
                    nowDis = ans[j];
                }
            }
            
            chk[now] = true;
            
            for(Edge e : nodes[now].a){
                if(!chk[e.des] && ans[now] + e.dis < ans[e.des]){
                    ans[e.des] = ans[now] + e.dis;
                }
            }
        }
        
        int cnt = 0;
        for(int a : ans){
            if(a <= K)
                cnt++;
        }
        // System.out.println(Arrays.toString(ans));
        return cnt ;
                
    }
    
    static class Node{
        int idx;
        ArrayList<Edge> a;
        public Node(int idx){
            this.idx = idx;
            a = new ArrayList<>();
        }
    }
    
    static class Edge{
        int dis, des;
        public Edge(int dis, int des){
            this.dis = dis;
            this.des = des;
        }
        @Override
        public String toString(){
            return "["+des+"/"+dis+"]";
        }
    }
        
    
}