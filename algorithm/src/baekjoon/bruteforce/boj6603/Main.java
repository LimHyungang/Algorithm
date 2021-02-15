package baekjoon.bruteforce.boj6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;

    static int K;
    static int[] S = null;
    static int[] lotto = null;
    static boolean[] check = null;

    public static void dfs(int dept) {
        if(dept == 6) {
            for(int i = 0; i < 6; i++) {
                sb.append(lotto[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < K; i++) {
            if(!check[i] && S[i] > lotto[dept - 1]) {
                lotto[dept] = S[i];
                check[i] = true;
                dfs(dept + 1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if(K == 0) break;

            S = new int[K];
            lotto = new int[6];
            check = new boolean[K];
            for(int i = 0; i < K; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < K; i++) {
                lotto[0] = S[i];
                check[i] = true;
                dfs(1);
                check[i] = false;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}