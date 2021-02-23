package baekjoon.dp.boj1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int n, ans;

    static int[][] map = null;
    static int[][] dp = null;  // dp[r][c] = (r, c)를 시작점으로 하여 나아갈 수 있는 최장경로
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1 ,1};

    public static void dfs(int r, int c) {
        dp[r][c] = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(-1 < nr && nr < n && -1 < nc && nc < n && map[r][c] < map[nr][nc]) {
                if(dp[nr][nc] == -1) {
                    dfs(nr, nc);
                }
                dp[r][c] = Math.max(dp[r][c], dp[nr][nc]);  // 상하좌우 중 최대값
            }
        }
        dp[r][c] += 1;  // 자기 자신에 대한 값 추가
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 초기화
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dfs(i, j);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.println(ans);
    }
}