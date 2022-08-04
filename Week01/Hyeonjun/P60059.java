package Programmers.Curriculum;

public class P60059 {
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        size = n + (m - 1) * 2;

        // board setting
        board = new boolean[size][size];
        for(int i = m - 1; i < n + m - 1; i++) {
            for(int j = m - 1; j < n + m - 1; j++) {
                board[i][j] = lock[i - m + 1][j - m + 1]==1;
            }
        }

        // board[i][j]에서 시작
        for(int i = 0; i < size - m + 1; i++) {
            for(int j = 0; j < size - m + 1; j++) {

                boolean[][] rKey = getCopy(key);

                for(int t = 0; t < 4; t++) {
                    // 키와 비교할 보드
                    boolean[][] temp = getCopy();

                    boolean disable = false;
                    // 키 입력
                    for(int x = i; x < i + m; x++) {
                        for(int y = j; y < j + m; y++) {
                            if(rKey[x - i][y - j] && temp[x][y]) {
                                disable = true;
                                break;
                            }
                            if(rKey[x - i][y - j] || temp[x][y]) temp[x][y] = true;
                            else temp[x][y] = false;
                        }
                    }

                    // print(temp);
                    // System.out.println();

                    // 열리는지 확인
                    if(!disable && isOpen(temp)) return true;


                    // rotate Key
                    rKey = rotate(rKey);
                }
            }
        }

        return false;
    }

    private int n, m, size;
    private boolean[][] board;

    private boolean[][] rotate(boolean[][] key) {
        boolean[][] arr = new boolean[m][m];
        for(int j = 0; j < m; j++) {
            for(int i = m - 1, y = 0; i >= 0; i--, y++) {
                arr[j][y] = key[i][j];
            }
        }
        return arr;
    }

    private boolean isOpen(boolean[][] board) {
        for(int i = m - 1; i < n + m - 1; i++) {
            for(int j = m - 1; j < n + m - 1; j++) {
                if(!board[i][j]) return false;
            }
        }
        return true;
    }

    private boolean[][] getCopy() {
        boolean[][] temp = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }

    private boolean[][] getCopy(int[][] key){
        boolean[][] rKey = new boolean[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                rKey[i][j] = key[i][j]==1;
            }
        }
        return rKey;
    }

    private void print(boolean[][] board){
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                System.out.print((board[i][j]?"1":"0") + " ");
            }
            System.out.println();
        }
    }
}
