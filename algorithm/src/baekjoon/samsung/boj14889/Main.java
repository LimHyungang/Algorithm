package baekjoon.samsung.boj14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, sTotal, lTotal, ans = 100000;
    static int[][] S;
    static int[] start, link;
    static boolean[] check;

    public static void calculate() {
        sTotal = 0;
        lTotal = 0;
        start = new int[n/2];
        link = new int[n/2];

        int sIdx = 0, lIdx = 0;
        for(int i = 0; i < n; i++) {
            if(check[i]) {
                start[sIdx++] = i;
            }else {
                link[lIdx++] = i;
            }
        }

        for(int i = 0; i < n/2; i++) {
            for(int j = i+1; j < n/2; j++) {
                sTotal += S[start[i]][start[j]] + S[start[j]][start[i]];
                lTotal += S[link[i]][link[j]] + S[link[j]][link[i]];
            }
        }
    }

    public static void dfs(int idx, int dept) {
        if(dept == n/2) {
            calculate();
            ans = Math.min(ans, Math.abs(sTotal - lTotal));
            return;
        }

        for(int i = idx; i < n; i++) {  // 중복조합 제외하기 위해 idx부터 검사
            if(!check[i]) {
                check[i] = true;
                dfs(i, dept + 1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        S = new int[n][n];
        check = new boolean[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }
}