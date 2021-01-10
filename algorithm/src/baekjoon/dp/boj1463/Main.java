package baekjoon.dp.boj1463;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i <= n; i++) {
            int temp = n;
            if(i % 3 == 0) temp = Math.min(temp, dp[i / 3]);
            if(i % 2 == 0) temp = Math.min(temp, dp[i / 2]);
            temp = Math.min(temp, dp[i - 1]);
            dp[i] = temp + 1;
        }
        System.out.println(dp[n]);
    }
}
