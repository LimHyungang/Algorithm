package baekjoon.dp.boj12865;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] W = new int[n+1];
        int[] V = new int[n+1];
        int[][] dp = new int[n+1][k+1];  // dp[n][k] = 1~N개의 물건으로 무게제한 K 범위에서 가능한 최대 V
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            W[i] = w;
            V[i] = v;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                if(W[i] > j) {  // 무게 제한 j로 W[i]이 감당이 안 되는 경우
                    dp[i][j] = dp[i-1][j];
                }else {  // W[i]를 포함해서 새로운 조합을 만들 수 있는 경우 (= j가 W[i] 감당 가능)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}