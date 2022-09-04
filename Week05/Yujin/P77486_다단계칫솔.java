import java.util.*;
class Solution {
    static int N, M;
        // N : enroll의 길이, 구성원 수, 
    static Node[] node;
    static Node top;
    static HashMap<String, Integer> map;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length;
        M = seller.length;
        node = new Node[N];
        map = new HashMap<>();
        top = new Node("-");
        for(int i = 0; i < N; i++){
            node[i] = new Node(enroll[i]);
            map.put(enroll[i], i);
            
            if("-".equals(referral[i]))
                node[i].head = top;
            else
                node[i].head = node[map.get(referral[i])];
        }
        
        for(int i = 0; i < M; i++){
            int money = amount[i]*100;
            Node now = node[map.get(seller[i])];
            while(true){
                if(now.head == null || money / 10 < 1 ){
                    
                    now.amount += money;
                    // System.out.println("end // "+now.toString());
                    break;
                }
                
                now.amount += money - money / 10;
                money = money / 10;
                // System.out.println(now.toString());
                now = now.head;
                
            }
        }
        
        
        
        int[] answer = new int[N];
        for(int i = 0; i < N; i++){
            answer[i] = node[i].amount;
        }
        return answer;
    }
    
    
    static class Node{
        Node head;
        String name;
        int amount;
        public Node(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return "name : " + this.name + ", amount : " + this.amount;
        }
    }
}