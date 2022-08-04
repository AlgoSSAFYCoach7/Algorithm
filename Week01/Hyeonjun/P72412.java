package Programmers.Curriculum;

import AlgoStudy.Week05.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class P72412 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        P72412 sol = new P72412();
        int[] answer = sol.solution(info, query);
        for(int i : answer) System.out.println(i);
    }

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        String[][] infos = new String[info.length][5];
        String[][] queries = new String[query.length][5];

        for(int i = 0; i < info.length; i++) {
            infos[i] = info[i].split(" ");
        }

        for(int i = 0; i < query.length; i++) {
            queries[i] = query[i].split(" and ");
        }

        for(String[] i : infos) {
            makeString(i, 0, "");
        }

        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for(int i = 0; i < queries.length; i++) {
            StringBuilder cur = new StringBuilder();
            for(int j = 0; j < 3; j++) {
                cur.append(queries[i][j]);
            }
            String[] temp = queries[i][3].split(" ");
            cur.append(temp[0]);

            int queryScore = Integer.parseInt(temp[1]);
            if(!map.containsKey(cur.toString())) continue;
            List<Integer> scores = map.get(cur.toString());
            answer[i] += binarySearch(scores, queryScore);
        }

        return answer;
    }

    private final HashMap<String, List<Integer>> map = new HashMap<>();

    private void makeString(String[] info, int idx, String str) {
        if(idx == 4) {
            int value = Integer.parseInt(info[4]);
            if(!map.containsKey(str)) map.put(str, new ArrayList<>());
            map.get(str).add(value);
            return;
        }
        makeString(info, idx + 1, str + info[idx]);
        makeString(info, idx + 1, str + "-");
    }

    private int binarySearch(List<Integer> scores, int targetScore) {
        int start = 0, end = scores.size() - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(scores.get(mid) < targetScore)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return scores.size() - start;
    }
}
