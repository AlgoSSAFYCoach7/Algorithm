import java.util.*;
// import java.lang.Object;
class Solution {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static Node[][] map;
    static HashSet<Record> set;
    static int R, C;
    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        map = new Node[R][C];
        set = new HashSet<>();
        for (int r = 0; r < R; r++) {
            char[] tmp = grid[r].toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = new Node(r, c, findType(tmp[c]));
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    Record now = new Record(r, c, d);
                    if (set.contains(now))
                        continue;
                    int cnt = findPath(now);
                    // System.out.println(cnt);
                    if (cnt != 0)
                        pq.add(cnt);
                }
            }
        }

        int[] ans = new int[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            ans[idx++] = pq.poll();
        }
        return ans;
    }

    static int findPath(Record pos) {
        int nowSize = set.size();
        Record tmp = pos;
        int nr = tmp.sr;
        int nc = tmp.sc;
        int ndir = tmp.dir;
        while (!set.contains(new Record(findNr(nr + dr[ndir]), findNc(nc + dc[ndir]), map[findNr(nr + dr[ndir])][findNc(nc + dc[ndir])].returnDir(ndir)))) {
            // System.out.println(tmp.toString());
            tmp = new Record(findNr(nr + dr[ndir]), findNc(nc + dc[ndir]), map[findNr(nr + dr[ndir])][findNc(nc + dc[ndir])].returnDir(ndir));
            nr = tmp.sr;
            nc = tmp.sc;
            ndir = tmp.dir;
            set.add(tmp);
        }

        return set.size() - nowSize;

    }

    static int findNr(int r) {
        return r >= 0 && r < R ? r : r < 0 ? R - 1 : 0;
    }

    static int findNc(int c) {
        return c >= 0 && c < C ? c : c < 0 ? C - 1 : 0;
    }


    static int findType(char tmp) {
        if (tmp == 'S')
            return 0;
        else if (tmp == 'R')
            return 1;
        else
            return 2;
    }

    static class Record {
        int sr, sc, dir;

        public Record(int sr, int sc, int dir) {
            this.sr = sr;
            this.sc = sc;
            this.dir = dir;
        }

        @Override
//        public boolean equals(Record o){
//            return this.sr == o.sr && this.sc == o.sc && this.dir == o.dir ? true : false;
//        }
        public boolean equals(Object obj) {
            if (obj instanceof Record) {
                Record record = (Record) obj;
                return this.sr == record.sr && this.sc == record.sc && this.dir == record.dir ? true : false;
            }
            return false;
        }


        @Override
        public int hashCode() {
            return Objects.hashCode("" + this.sr + "/" + this.sc + "/" + this.dir);

        }

        @Override
        public String toString(){
            return "r : " + this.sr + ", c : " + this.sc + ", dir : " + this.dir;
        }
    }

    static class Node {
        int r, c, type;

        // type
        //     0 : S
        //     1 : R
        //     2 : L
        public Node(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }

        public int returnDir(int inputDir) {
            return this.type == 0 ? inputDir : (this.type == 1 ? this.calcDir(inputDir + 1) : this.calcDir(inputDir - 1));
        }

        public int calcDir(int inputDir) {
            return inputDir >= 0 && inputDir < 4 ? inputDir : inputDir < 0 ? inputDir + 4 : inputDir - 4;
//            return inputDir < 0 ? inputDir + 4 : (inputDir > 3 ? inputDir - 4 : inputDir);
        }
    }
}