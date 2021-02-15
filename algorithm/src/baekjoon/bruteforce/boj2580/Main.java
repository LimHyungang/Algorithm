package baekjoon.bruteforce.boj2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[][] map = null;
    static int[][] zero = null;
    static boolean[][] check = null;
    static int zeroCnt;

    public static boolean isPossible(int r, int c) {
        // 행 검사
        for(int i = 0; i < 9; i++) {
            if(i == c) continue;
            if(map[r][i] == map[r][c]) {
                return false;
            }
        }

        // 열 검사
        for(int i = 0; i < 9; i++) {
            if(i == r) continue;
            if(map[i][c] == map[r][c]) {
                return false;
            }
        }

        // 3x3 사각형 검사
        int row = r / 3 * 3;
        int col = c / 3 * 3;
        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if(i == r && j == c) continue;
                if(map[i][j] == map[r][c]) {
                    return false;
                }
            }
        }

        return true;
    }


    public static void dfs(int deptNow, int dept) {
        if(deptNow == dept) {  // 모든 0 처리 완료
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        int r = zero[deptNow][0];
        int c = zero[deptNow][1];
        for(int i = 1; i <= 9; i++) {
            map[r][c] = i;
            if(isPossible(r, c)) {  // 새로운 칸 채울 때마다 검사해서 불필요한 재귀를 막는다.
                dfs(deptNow + 1, dept);
            }
            map[r][c] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        zero = new int[81][2];
        check = new boolean[9][9];

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    zero[zeroCnt][0] = i;
                    zero[zeroCnt][1] = j;
                    zeroCnt += 1;
                }
            }
        }

        int r = zero[0][0];
        int c = zero[0][1];
        for(int i = 1; i <= 9; i++) {
            map[r][c] = i;
            if(isPossible(r, c)) {
                dfs(1, zeroCnt);
            }
            map[r][c] = 0;
        }
    }
}