package baekjoon.samsung.boj20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Fireball {
    int r, c, m, s, d;
    boolean flag;
    Fireball(int r, int c, int m, int s, int d, boolean flag) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
        this.flag = flag;
    }
}

public class Main {
    static int n, m, k, ans;
    static boolean turnFlag;
    static ArrayList<Fireball>[][] map;
    static int[] rArr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] cArr = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void calculate() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j].isEmpty()) continue;
                for(Fireball f : map[i][j]) {
                    ans += f.m;
                }
            }
        }
    }

    public static void moveBall() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j].isEmpty()) continue;

                for(int idx = 0; idx < map[i][j].size(); idx++) {
                    Fireball f = map[i][j].get(idx);
                    if(f.flag == turnFlag) continue;  // 이번 턴에 이동해서 온 것은 또 이동시키면 안 딤

                    int nr = f.r + rArr[f.d] * (f.s % n);
                    int nc = f.c + cArr[f.d] * (f.s % n);
                    if(nr > 0) nr %= n;
                    if(nc > 0) nc %= n;
                    if(nr < 0) nr = n - Math.abs(nr);
                    if(nc < 0) nc = n - Math.abs(nc);
                    // (f.s % n)을 곱하기 때문에 n-Math.abs()는 반드시 양수 보장

                    f.r = nr;
                    f.c = nc;
                    f.flag = turnFlag;
                    map[nr][nc].add(f);
                    map[i][j].remove(idx--);
                }
            }
        }
    }

    public static void sumAndDivide() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int size = map[i][j].size();
                if(size < 2) continue;

                // sum
                int mSum = 0, sSum = 0;
                int oddCnt = 0, evenCnt = 0;
                for(Fireball f : map[i][j]) {
                    mSum += f.m;
                    sSum += f.s;
                    if(f.d % 2 != 0) oddCnt += 1;
                    if(f.d % 2 == 0) evenCnt += 1;
                }
                map[i][j].clear();  // 합쳐진 파이어볼들은 삭제해준다

                // divide
                int nm = mSum/5, ns = sSum/size, d = 1;
                if(nm == 0) continue;  // 질량이 0인 파이어볼은 사라진다
                if(oddCnt == size || evenCnt == size)
                    d = 0;  // 모두 홀수 or 모두 짝수이면 0,2,4,6
                for(int nd = d; nd <= 7; nd+=2) {
                    Fireball f = new Fireball(i, j, nm, ns, nd, turnFlag);
                    map[i][j].add(f);
                }
            }
        }
    }

    public static void process() {
        while(k-- > 0) {
            moveBall();
            sumAndDivide();
            turnFlag = !turnFlag;
        }
        calculate();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Fireball f = new Fireball(r, c, m, s, d, true);
            map[r][c].add(f);
        }

        process();
        System.out.println(ans);
    }
}