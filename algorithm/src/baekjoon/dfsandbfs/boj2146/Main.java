package baekjoon.dfsandbfs.boj2146;

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
    static int[][] component = null;
    static boolean[][] check = null;

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    static int n;

    public static void dfs(int r, int c, int comp) {
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(0 <= nr && nr < n && 0 <= nc && nc < n &&
                    map[nr][nc] == 1 && !check[nr][nc]) {
                check[nr][nc] = true;
                component[nr][nc] = comp;
                dfs(nr, nc, comp);
            }
        }
    }

    public static int bfs() {
        int ans = n * n;  // 다리의 길이는 n^2을 넘을 수 없다
        Queue<Pair> que = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {  // 일단 모든 섬들을 큐에 담는다. 한 섬당 한 칸 씩 차례대로 다리를 올릴 것이다
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    que.add(new Pair(i, j));
                }
            }
        }

        while(!que.isEmpty()) {
            Pair p = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];
                if(0 <= nr && nr < n && 0 <= nc && nc < n) {
                    if(map[nr][nc] == 0) {
                        map[nr][nc] = map[p.r][p.c] + 1;  // 다리를 한 칸 올린다
                        component[nr][nc] = component[p.r][p.c];  // 새로 올라간 다리도 섬의 일부로 친다
                        que.add(new Pair(nr, nc));
                    }else {
                        if(component[nr][nc] == component[p.r][p.c]) {  // 같은 섬은 패스
                            continue;
                        }else {  // 다른 섬 만남
                            // 다른 섬을 만났을 때 (우리 섬에서 올린 다리길이 + 다른 섬에서 올린 다리길이)가 합쳐진 다리의 길이이다
                            // 두 섬을 잇는 다리가 완성되었으니 더이상 큐에 추가하지 않는다
                            int bridge = (map[p.r][p.c] - 1) + (map[nr][nc] - 1); // 섬의 숫자 1까지 길이에 포함되었으니 -1
                            ans = Math.min(ans, bridge);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        component = new int[n][n];
        check = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int comp = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(map[i][j] == 1 && !check[i][j]) {
                    check[i][j] = true;
                    component[i][j] = comp;
                    dfs(i, j, comp);
                    comp += 1;
                }
            }
        }
        int ans = bfs();
        System.out.println(ans);
    }
}