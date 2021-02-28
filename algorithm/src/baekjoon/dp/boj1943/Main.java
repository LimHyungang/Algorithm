package baekjoon.dp.boj1943;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = 3;
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[100001];  // 원장님이 주는 금액의 최대는 10만원
            boolean[] dp = new boolean[100001];  // i원 만들기 가능?

            int total = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int quantity = Integer.parseInt(st.nextToken());
                coins[coin] = quantity;
                total += coin * quantity;  // 받은 총 금액 구함

                for(int j = 1; j <= quantity; j++) {
                    dp[coin * j] = true;  // 각 종류의 동전으로 만들 수 있는 액수 먼저 체크
                }
            }

            // 굳이 dp[] 다 안 채워도 되는 경우
            if(total % 2 == 1) {
                System.out.println(0);
                continue;
            }else if(dp[total / 2]) {
                System.out.println(1);
                continue;
            }

            // 주어진 동전으로 (total / 2)원을 만들 수 있는지 확인하면 될 듯?
            dp[0] = true;
            for(int i = 1; i <= total/2; i++) {
                if(dp[i]) continue;  // 이미 만들 수 있는 것 확인 됨

                for(int j = i; j >= 0; j--) {
                    if(dp[i - j] && coins[j] != 0) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            System.out.println(dp[total / 2] ? 1 : 0);
        }
    }
}