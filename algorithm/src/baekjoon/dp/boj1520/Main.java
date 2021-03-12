package baekjoon.dp.boj1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = null;
    static int[][] dp = null;  // dp[i][j] == (i, j)에서 목적지로 갈 수 있는 경우의 수
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};
    static int n, m;

    public static void dfs(int r, int c) {
        if(r == n-1 && c == m-1) {  // 목표 지점에 도착
            dp[r][c] = 1;
            return;
        }

        dp[r][c] = 0;  // 방문 표시
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(-1 < nr && nr < n && -1 < nc && nc < m) {
                if(map[r][c] > map[nr][nc] ) {
                    if(dp[nr][nc] == -1) {
                        dfs(nr, nc);
                    }
                    dp[r][c] += dp[nr][nc];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
}