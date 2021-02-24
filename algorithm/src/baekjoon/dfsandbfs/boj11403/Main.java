package baekjoon.dfsandbfs.boj11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;

    static int[][] map = null;
    static int[][] ans = null;
    static int n;

    public static void dfs(int start, int now) {
        for(int next = 0; next < n; next++) {
            if(map[now][next] == 1 && ans[start][next] == 0) {  // ans로 방문 여부 체크
                ans[start][next] = 1;
                dfs(start, next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        ans = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1 && ans[i][j] == 0) {
                    ans[i][j] = 1;
                    dfs(i, j);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}