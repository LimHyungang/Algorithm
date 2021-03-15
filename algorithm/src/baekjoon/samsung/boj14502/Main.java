package baekjoon.samsung.boj14502;

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
    static int n, m, ans;
    static int[][] map;
    static int[][] temp;
    static int[][] virus;

    static int zeroIdx;
    static int[][] zero;
    static boolean[] zeroCheck;

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static int calculate() {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(virus[i][j] == 0)
                    cnt += 1;
            }
        }
        return cnt;
    }

    public static void dfs(int dept, int idx) {
        if(dept == 3) {
            bfs();  // 바이러스 퍼뜨리기
            return;
        }

        for(int i = idx + 1; i < zeroIdx; i++) {
            if(!zeroCheck[i]) {
                zeroCheck[i] = true;
                temp[zero[i][0]][zero[i][1]] = 1;
                dfs(dept + 1, i);
                temp[zero[i][0]][zero[i][1]] = 0;
                zeroCheck[i] = false;
            }
        }
    }

    public static void bfs() {
        // deep copy
        for(int i = 0; i < n; i++) {
            virus[i] = temp[i].clone();
        }

        // 큐 초기화
        Queue<Pair> que = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(virus[i][j] == 2) {
                    que.add(new Pair(i, j));
                }
            }
        }

        while(!que.isEmpty()) {
            Pair p = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];
                if(-1 < nr && nr < n && -1 < nc && nc < m) {
                    if(virus[nr][nc] == 0) {
                        virus[nr][nc] = 2;
                        que.add(new Pair(nr, nc));
                    }
                }
            }
        }

        // 바이러스 확산 완료됐으면 안전 영역 계산
        int safe = calculate();
        ans = Math.max(ans, safe);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        temp = new int[n][m];
        virus = new int[n][m];
        zero = new int[n*m][2];
        zeroCheck = new boolean[n*m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    zero[zeroIdx][0] = i;
                    zero[zeroIdx++][1] = j;
                }
            }
        }

        // 벽 3개 놓기 (백트래킹)
        // 바이러스 퍼트리기 (bfs)
        // 다 퍼뜨린 후 안전 영역 세기

        for(int i = 0; i < zeroIdx; i++) {
            // deep copy
            for(int j = 0; j < n; j++) {
                temp[j] = map[j].clone();
            }

            zeroCheck[i] = true;
            temp[zero[i][0]][zero[i][1]] = 1;
            dfs(1, i);
            temp[zero[i][0]][zero[i][1]] = 0;
            zeroCheck[i] = false;
        }

        System.out.println(ans);
    }
}