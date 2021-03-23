package baekjoon.samsung.boj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, m, ans = Integer.MAX_VALUE;
    static int[][] map;  // 사실 이건 굳이 안 써도 된다
    static boolean[] check;
    static ArrayList<Pair> stores;  // 치킨집들의 좌표
    static ArrayList<Pair> houses;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static int calculate() {  // 집과 치킨집 매핑 후 도시의 치킨 거리 계산
        int sum = 0;
        for(int i = 0; i < houses.size(); i++) {
            int hr = houses.get(i).r;
            int hc = houses.get(i).c;
            int dist = Integer.MAX_VALUE;

            for(int j = 0; j < stores.size(); j++) {
                if(check[j]) {
                    int sr = stores.get(j).r;
                    int sc = stores.get(j).c;
                    dist = Math.min(dist, Math.abs(hr - sr) + Math.abs(hc - sc));
                }
            }
            sum += dist;
        }
        return sum;
    }

    public static void dfs(int dept, int idx) {  // 치킨집 m개 조합
        if(dept == m) {
            ans = Math.min(ans, calculate());
            return;
        }

        for(int i = idx; i < stores.size(); i++) {
            // 남은 거 다 뽑아도 m개 채우지 모한다면 더 이상 재귀하지 않음
            if(!check[i] && m - dept <= stores.size() - idx) {
                check[i] = true;
                dfs(dept+1, i+1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        stores = new ArrayList<>();
        houses = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    stores.add(new Pair(i, j));
                }else if(map[i][j] == 1) {
                    houses.add(new Pair(i, j));
                }
            }
        }
        check = new boolean[stores.size()];

        dfs(0, 0);
        System.out.println(ans);
    }
}