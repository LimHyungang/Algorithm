package baekjoon.bruteforce.boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static char[][] map = null;
    static boolean[] check = null;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};
    static int R, C, ans;

    public static void dfs(int r, int c, int dept) {
        ans = Math.max(ans, dept);

        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];

            if(-1 < nr && nr < R && -1 < nc && nc < C) {
                char ch = map[nr][nc];
                if(!check[ch - 65]) {
                    check[ch - 65] = true;
                    dfs(nr, nc, dept + 1);
                    check[ch - 65] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        check = new boolean[26];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        char ch = map[0][0];
        check[ch - 65] = true;
        dfs(0, 0, 1);
        System.out.println(ans);
    }
}