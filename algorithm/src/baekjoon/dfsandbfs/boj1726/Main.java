package baekjoon.dfsandbfs.boj1726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r, c, dir, cnt;
    Pair(int r, int c, int dir, int cnt) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.cnt = cnt;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[][] map = null;
    static boolean[][][] check = null;

    static Pair begin = null;
    static Pair end = null;
    static int N, M, ans;

    // [0, 1, 2, 3, 4] = [0, 동, 서, 남, 북] 방향 맞춰줘야 한다
    static int[] rArr = {0, 0, 0, 1, -1};
    static int[] cArr = {0, 1, -1, 0, 0};

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(begin);
        check[begin.r][begin.c][begin.dir] = true;

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int r = p.r, c = p.c, dir = p.dir, cnt = p.cnt;
            if(r == end.r && c == end.c && dir == end.dir) {
                ans = cnt;
                return;
            }

            // 현재 바라본 방향에서 1, 2, 3칸 이동 명령
            for(int i = 1; i <= 3; i++) {
                int nr = r + rArr[dir] * i;  // 현재 방향으로 i칸 이동
                int nc = c + cArr[dir] * i;
                if(1 <= nr && nr <= N && 1 <= nc && nc <= M) {  // 범위 체크
                    if(map[nr][nc] == 0 && !check[nr][nc][dir]) {
                        que.add(new Pair(nr, nc, dir, cnt + 1));
                        check[nr][nc][dir] = true;
                    }else if(map[nr][nc] == 1) {
                        break;  // 벽을 만나면 더 건너뛸 수 없음
                    }
                }
            }

            // 현재 방향을 제외한 모든 방향으로 이동
            // 180도 방향에 대해서는 +2 처리하고 나머진 +1
            for(int i = 1; i <= 4; i++) {  // i = 이동할 방향
                if(dir != i && !check[r][c][i]) {  // 현재 위치에서 방향만 도는 것임
                    int inst = 1;
                    if(dir == 1) {
                        if(i == 2) inst++;
                    }else if(dir == 2) {
                        if(i == 1) inst++;
                    }else if(dir == 3) {
                        if(i == 4) inst++;
                    }else if(dir == 4){
                        if(i == 3) inst++;
                    }
                    que.add(new Pair(r, c, i, cnt + inst));
                    check[r][c][i] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        check = new boolean[N+1][M+1][5];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        begin = new Pair(r, c, dir, 0);

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        end = new Pair(r, c, dir, 0);

        bfs();
        System.out.println(ans);
    }
}