package baekjoon.dp.boj2225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];

        /*
        n은 1~200인데 i 반복은 0부터 시작하는 이유?
        -> [0][1]~[0][k] 를 먼저 구해놔야 다음 반복의 점화식에서 사용할 수 있다.

        [0][1]~[0][k] 는 어차피 전부 1이니까 (0만 k개 더하는 경우)
        좀 더 명확하게 알아보기 위해 초기화용 반복을 따로 돌리고 본 반복은 i = 1 부터 시작하는 것도 방법이다.
         */

        dp[0][0] = 1;
        for(int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int m = 0; m <= i; m++) {
                    dp[i][j] += dp[i - m][j - 1];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}