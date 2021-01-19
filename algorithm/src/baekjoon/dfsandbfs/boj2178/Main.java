package baekjoon.dfsandbfs.boj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
    int r;
    int c;
    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[][] map = null;
    static boolean[][] check = null;

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    static int n, m;

    public static void bfs(int r, int c) {
        Queue<Pair> que = new ArrayDeque<>();
        check[r][c] = true;
        que.add(new Pair(r, c));

        while(!que.isEmpty()) {
            Pair p = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];
                if(0 <= nr && nr < n && 0 <= nc && nc < m &&
                        !check[nr][nc] && map[nr][nc] != 0) {
                    check[nr][nc] = true;
                    map[nr][nc] = map[p.r][p.c] + 1;
                    que.add(new Pair(nr, nc));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        map = new int[n][m];
        check = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                map[i][j] = ch[j] - '0';
            }
        }
        bfs(0, 0);
        System.out.println(map[n-1][m-1]);
    }
}