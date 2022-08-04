package Programmers.Curriculum;

import java.util.HashSet;
import java.util.Set;

// 칡이 몸에 좋다고 합니다.
public class P42890 {
    public int solution(String[][] relations) {
        this.relations = relations;
        colSize = relations.length;
        rowSize = relations[0].length;
        picked = new int[rowSize];
        visited = new boolean[rowSize];
        set = new HashSet<>();
        for(int i = 1; i <= rowSize; i++) {
            comb(0, 0, i);
        }

        return answer;
    }

    private String[][] relations;
    private boolean[] visited;
    private int[] picked;
    private int colSize, rowSize, answer;
    private Set<String> set;

    private void comb(int start, int count, int size) {
        if(count == size) {
            String key = makeKey(size);
            if(!checkMinimality(key, size)) return;
            if(checkUniqueness(size)) set.add(key);
            return;
        }
        for(int i = start; i < rowSize; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            picked[count] = i;
            comb(i + 1, count + 1, size);
            visited[i] = false;
        }
    }

    private String makeKey(int size) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(String.valueOf(picked[i]));
        }
        return sb.toString();
    }

    private boolean checkMinimality(String key, int size){
        for(String cur : set) {
            if(cur.length() >= key.length()) continue;
            boolean isContain = true;
            for(char c : cur.toCharArray()) {
                if(!key.contains(c + "")) {
                    isContain = false;
                    break;
                }
            }
            if(isContain) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUniqueness(int size) {
        answer++;
        Set<String> curSet = new HashSet<>();
        for(String[] relation : relations) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < size; i++) {
                sb.append(relation[picked[i]]);
            }
            if(curSet.contains(sb.toString())) {
                answer--;
                return false;
            }
            curSet.add(sb.toString());
        }
        return true;
    }
    // 칡칡폭폭
}
