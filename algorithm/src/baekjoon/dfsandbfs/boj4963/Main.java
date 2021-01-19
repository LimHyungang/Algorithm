package baekjoon.dfsandbfs.boj4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = null;
    static boolean[][] check = null;

    static int[] rArr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] cArr = {0, 0, -1, 1, 1, -1, -1, 1};

    static int w, h;

    public static void dfs(int r, int c) {
        for(int i = 0; i < 8; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(0 <= nr && nr < h && 0 <= nc && nc < w &&
                    !check[nr][nc] && map[nr][nc] == 1) {
                check[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            map = new int[h][w];
            check = new boolean[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(!check[i][j] && map[i][j] == 1) {
                        check[i][j] = true;
                        dfs(i, j);
                        cnt += 1;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}