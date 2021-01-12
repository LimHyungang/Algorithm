package baekjoon.dp.boj11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        for(int i = 1; i < n; i++) {
            dp[i] = arr[i];
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[j] + arr[i] > dp[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }
        }

        OptionalInt ans = Arrays.stream(dp).max();
        System.out.println(ans.getAsInt());
    }
}
