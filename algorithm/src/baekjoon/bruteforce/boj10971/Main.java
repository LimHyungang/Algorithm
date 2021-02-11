package baekjoon.bruteforce.boj10971;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = null;
    static StringTokenizer st = null;

    static int[][] map = null;
    static int[] path = null;
    static boolean[] check = null;

    static int n, ans = 10000000;  // 초기값 1천만

    public static int calculate() {
        int result = 0;
        for(int i = 0; i < n; i++) {
            int from = path[i];
            int to = path[i+1];
            result += map[from][to];
        }
        return result;
    }

    public static void dfs(int start, int now, int deptNow, int dept) {
        if(deptNow == dept) {
            if(map[now][start] != 0) {
                path[deptNow] = start;
                ans = Math.min(ans, calculate());
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!check[i] && map[now][i] != 0) {
                path[deptNow] = i;
                check[i] = true;
                dfs(start, i, deptNow + 1, dept);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        path = new int[n + 1];
        check = new boolean[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            path[0] = i;
            check[i] = true;
            dfs(i, i, 1, n);
            check[i] = false;
        }
        System.out.println(ans);
    }
}