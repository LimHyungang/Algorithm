package baekjoon.dfsandbfs.boj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r, c, cnt, destroy;
    Pair(int r, int c, int cnt, int destroy) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        this.destroy = destroy;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[][] map = null;
    static boolean[][][] check = null;
    static int N, M, ans = -1;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(0, 0, 1, 0));
        check[0][0][0] = true;

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int r = p.r;
            int c = p.c;
            int cnt = p.cnt;
            int destroy = p.destroy;

            if(r == N - 1 && c == M - 1) {
                ans = cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r + rArr[i];
                int nc = c + cArr[i];

                if(-1 < nr && nr < N && -1 < nc && nc < M) {
                    if(map[nr][nc] == 1) {  // 벽이면??
                        if(destroy == 0 && !check[nr][nc][1]) {  // 이전에 부순적 없으면 부수고 이동
                            que.add(new Pair(nr, nc, cnt + 1,  destroy + 1));
                            check[nr][nc][destroy] = true;
                        }
                    }else{  // 벽이 아니면??
                        if(!check[nr][nc][destroy]) {  // 현재 destroy 횟수로 다음 좌표 갈 수 있는지만
                            que.add(new Pair(nr, nc, cnt + 1, destroy));  // destroy 유지하면서 이동
                            check[nr][nc][destroy] = true;
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

        map = new int[N][M];
        check = new boolean[N][M][2];
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