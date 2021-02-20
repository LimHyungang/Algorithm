package baekjoon.dfsandbfs.boj14442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r, c, d, cnt;
    Pair(int r, int c, int d, int cnt) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.cnt = cnt;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[][] map = null;
    static boolean[][][] check = null;
    static int N, M, K, ans = -1;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(0, 0, 0, 1));
        check[0][0][0] = true;

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int r = p.r, c = p.c, d = p.d, cnt = p.cnt;
            if(r == N - 1 && c == M - 1) {
                ans = cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r + rArr[i];
                int nc = c + cArr[i];
                if(-1 < nr && nr < N && -1 < nc && nc < M) {
                    if(map[nr][nc] == 1) {  // 다음 좌표가 벽
                        if(d < K && !check[nr][nc][d+1]) {  // 아직 벽을 더 뚫을 수 있으면 계속 진행
                            que.add(new Pair(nr, nc, d + 1, cnt + 1));
                            check[nr][nc][d+1] = true;
                        }
                    }else {  // 다음 좌표 뚫려있음
                        if(!check[nr][nc][d]) {
                            que.add(new Pair(nr, nc, d, cnt + 1));
                            check[nr][nc][d] = true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        check = new boolean[N][M][K+1];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(ans);
    }
}