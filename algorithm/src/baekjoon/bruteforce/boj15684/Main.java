package baekjoon.bruteforce.boj15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;

    static int n, h, m, ans = -1;
    static int[][] map = null;
    static boolean complete;

    // 위는 검사하지 않는다. 양 옆에 1 있는지 먼저 체크. 없으면 아래로
    static int[] rArr = {0, 0};  // 좌 우
    static int[] cArr = {-1 ,1};  // 좌 우

    public static boolean isPossible() {
        for(int i = 1; i <= n; i++) {  // 모든 열 검사
            int r = 1;
            int c = i;
            for(int j = 1; j <= h; j++) {
                if(map[r][c-1] == 1) {  // 왼쪽 이동 가능?
                    c -= 1;
                    r += 1;
                    continue;
                }
                if(map[r][c] == 1) {  // 오른쪽 이동 가능?
                    c += 1;
                    r += 1;
                    continue;
                }
                r += 1;  // 양 옆 이동 불가면 아래로 이동

            }
            if(c != i) return false;
        }
        return true;
    }

    public static void dfs(int r, int c, int deptNow, int dept) {
        if(complete) return;
        if(deptNow == dept) {
            if(isPossible()) {
                ans = deptNow;  // dept는 1~3 순서로 올라가므로 굳이 대소비교 하지 않고 바로 넣어도 된다
                complete = true;
            }
            return;
        }

        // 이제 여기서 가로실선을 하나씩 놓자
        for(int i = r; i <= h; i++) {  // 이전 행은 다시 확인할 필요 없음
            for(int j = 1; j < n; j++) {  // 마지막 열에는 놓을 수 없다
                // 0열은 사용하지 않는 열이고 n열은 애초에 1이 올 수 없는 열이므로 범위 문제 없음
                if(map[i][j] == 0 && map[i][j-1] == 0 && map[i][j+1] == 0) {
                    map[i][j] = 1;
                    dfs(i, j, deptNow + 1, dept);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());  // N, M, H 순 입력

        // N : 세로 실선 (2 ~ 20)
        // H : 가로 점선 (1 ~ 30)
        // M : 가로 실선
        // H행 N열
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h+2][n+1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        for(int i = 0; i <= 3; i++) {
            dfs(1, 1, 0, i);
            if(complete)
                break;
        }
        System.out.println(ans);
    }
}