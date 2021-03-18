package baekjoon.samsung.boj14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static int[][] map;
    static boolean[][] check;
    static int[] rArr = {-1, 0, 1, 0};  // 0123 == 북동남서
    static int[] cArr = {0, 1, 0, -1};

    public static void dfs(int r, int c, int d) {
        int nd = d;  // 현재 방향 d는 유지해야함. 다 돌고 나서 뒷칸으로 이동하는 경우를 위해.
        for (int i = 0; i < 4; i++) {
            // 방향 전환
            nd -= 1;  // 왼쪽 방향
            if (nd == -1) nd = 3;

            int nr = r + rArr[nd];
            int nc = c + cArr[nd];
            if (-1 < nr && nr < n && -1 < nc && nc < m) {
                if (!check[nr][nc] && map[nr][nc] == 0) {
                    check[nr][nc] = true;
                    ans += 1;
                    dfs(nr, nc, nd);
                    return;  // 나머지 방향들은 어차피 이 칸을 뒷칸으로 하여 돌아오는 재귀에서 수행하게 된다
                }
            }
        }

        nd = d - 2;  // 뒷칸
        if (nd == -1) nd = 3;
        if (nd == -2) nd = 2;

        int nr = r + rArr[nd];
        int nc = c + cArr[nd];
        if(-1 < nr && nr < n && -1 < nc && nc < m && map[nr][nc] != 1) {  // 뒷칸으로 갈 수 있는지?
            // 여기선 ans += 1, check[][] = true 해주면 안 됨
            // 지우면서 갈 수 있는 칸이라면 위의 for문에서 재귀했을것임
            // 이 경우는 지우지 않고 단순히 칸만 뒤로 이동하는 경우임
            dfs(nr, nc, d);  // 방향은 유지하면서 칸만 뒤로 이동
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check[startR][startC] = true;
        ans += 1;  // 최초 위치는 항상 빈칸임을 보장
        dfs(startR, startC, startD);
        System.out.println(ans);
    }
}