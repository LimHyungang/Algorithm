package baekjoon.dfsandbfs.boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

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
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    static int n, m;

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) {
                    que.add(new Pair(i, j));
                }
            }
        }

        while(!que.isEmpty()) {
            Pair p  = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];
                if(0 <= nr && nr < n && 0 <= nc && nc < m &&
                        map[nr][nc] == 0) {
                    map[nr][nc] = map[p.r][p.c] + 1;
                    que.add(new Pair(nr, nc));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());  // 열
        n = Integer.parseInt(st.nextToken());  // 행
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                if(max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }
        System.out.println(max - 1);
    }
}