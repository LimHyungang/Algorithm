package baekjoon.bruteforce.boj1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[][] map = null;
    static int[][] dist = null;
    static int N, M;

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1 ,1};

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        Queue<Pair> nextQue = new ArrayDeque<>();
        que.add(new Pair(0, 0));
        dist[0][0] = 0;

        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(-1 < nr && nr < N && -1 < nc && nc < M) {
                    if(dist[nr][nc] == -1) {  // checking
                        if(map[nr][nc] == 0) {
                            dist[nr][nc] = dist[p.r][p.c];
                            que.add(new Pair(nr, nc));
                        }else {
                            dist[nr][nc] = dist[p.r][p.c] + 1;
                            nextQue.add(new Pair(nr, nc));
                        }
                    }
                }
            }

            if(que.isEmpty()) {
                que = nextQue;
                nextQue = new ArrayDeque<>();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                dist[i][j] = -1;
            }
        }

        bfs();
        System.out.println(dist[N-1][M-1]);
    }
}