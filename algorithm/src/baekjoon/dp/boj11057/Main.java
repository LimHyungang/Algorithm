package baekjoon.dp.boj11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][10];
        for(int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
                dp[i][j] %= 10007;
            }
        }

        int ans = 0;
        for(int num : dp[n]) {
            ans += num;
        }
        ans %= 10007;
        System.out.println(ans);
    }
}