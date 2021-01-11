package baekjoon.dp.boj9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[3][n+1];
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            // 0행은 무시. dp랑 행 라인 맞추려면 0행 있는 게 편함
            for(int i = 1; i <= n; i++) {
                arr[1][i] = Integer.parseInt(st1.nextToken());
                arr[2][i] = Integer.parseInt(st2.nextToken());
            }

            // n개의 열에서 선택안하기, 1행선택하기, 2행선택하기
            int[][] dp = new int[3][n+1];
            dp[0][1] = 0;
            dp[1][1] = arr[1][1];
            dp[2][1] = arr[2][1];

            // 2~n 열
            for(int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1]));
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + arr[1][i];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + arr[2][i];
            }
            int ans = Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n]));
            System.out.println(ans);
        }
    }
}