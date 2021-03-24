package baekjoon.samsung.boj15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
    int r, c, cctv, dir;
    Pair(int r, int c, int cctv, int dir) {
        this.r = r;
        this.c = c;
        this.cctv = cctv;
        this.dir = dir;
    }
}

public class Main {
    static int n, m, k, ans = Integer.MAX_VALUE;
    static ArrayList<Pair> cList;
    static int[][] map;
    static int[][] temp;
    static int[] rArr = {-1, 0, 1, 0};  // 북동남서
    static int[] cArr = {0, 1, 0, -1};

    public static void calculate() {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(temp[i][j] == 0)
                    cnt += 1;
            }
        }
        ans = Math.min(ans, cnt);
    }

    public static void fillArr(int nr, int nc, int dir) {
        while(-1 < nr && nr < n && -1 < nc && nc < m && map[nr][nc] != 6) {
            temp[nr][nc] = 7;
            nr += rArr[dir];
            nc += cArr[dir];
        }
    }

    public static void monitor() {
        for(int i = 0; i < n; i++) {  // 메모리 down, 시간 up
            for(int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }

//        for(int i = 0; i < n; i++) {  // 메모리 up, 시간 down
//            temp[i] = map[i].clone();
//        }

        // 7이면 감시 영역
        for(Pair p : cList) {
            int nr = p.r, nc = p.c, dir = p.dir;
            switch(p.cctv) {
                case 1 :
                    fillArr(nr, nc, dir);
                    break;
                case 2 :
                    fillArr(nr, nc, dir);
                    nr = p.r; nc = p.c;
                    dir = (dir + 2) % 4;
                    fillArr(nr, nc, dir);
                    break;
                case 3 :
                    for(int i = 0; i < 2; i++) {
                        nr = p.r; nc = p.c;
                        dir = (dir + i) % 4;
                        fillArr(nr, nc, dir);
                    }
                    break;
                case 4 :
                    for(int i = 0; i < 3; i++) {
                        nr = p.r; nc = p.c;
                        dir = (dir + i) % 4;
                        fillArr(nr, nc, dir);
                    }
                    break;
                case 5 :
                    for(int i = 0; i < 4; i++) {
                        nr = p.r; nc = p.c;
                        dir = (dir + i) % 4;
                        fillArr(nr, nc, dir);
                    }
                    break;
            }
        }
    }

    public static void dfs(int dept) {
        if(dept == k) {
            monitor();
            calculate();
            return;
        }

        Pair p = cList.get(dept);
        switch(p.cctv ) {
            case 2 :
                p.dir = 0; dfs(dept + 1);
                p.dir = 1; dfs(dept + 1);
                break;
            case 5 :
                p.dir = 0; dfs(dept + 1);
                break;
            default :
                p.dir = 0; dfs(dept + 1);
                p.dir = 1; dfs(dept + 1);
                p.dir = 2; dfs(dept + 1);
                p.dir = 3; dfs(dept + 1);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        temp = new int[n][m];
        cList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(1 <= map[i][j] && map[i][j] <= 5) {
                    cList.add(new Pair(i, j, map[i][j], 0));
                }
            }
        }
        k = cList.size();

        dfs(0);
        System.out.println(ans);
    }
}