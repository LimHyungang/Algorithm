package programmers.pg1829;

class Solution {
    int m, n;
    int numberOfArea;
    int sizeOfArea, maxSizeOfOneArea;
    int[] rArr = {-1, 1, 0, 0};
    int[] cArr = {0, 0, -1, 1};

    public void dfs(int r, int c, int dept, int[][] map, boolean[][] check) {
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(-1 < nr && nr < m && -1 < nc && nc < n) {
                if(!check[nr][nc] && map[nr][nc] == map[r][c]) {
                    sizeOfArea += 1;
                    check[nr][nc] = true;
                    dfs(nr, nc, dept + 1, map, check);
                }
            }
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;

        boolean[][] check = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!check[i][j] && picture[i][j] != 0) {
                    sizeOfArea = 1;
                    check[i][j] = true;
                    dfs(i, j, 1, picture, check);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfArea);
                    numberOfArea += 1;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }
}