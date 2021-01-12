package baekjoon.dp.boj11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp1 = new int[n];  // 가장 긴 증가 부분수열
        int[] dp2 = new int[n];  // 가장 긴 감소 부분수열
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp1[0] = 1;
        for(int i = 1; i < n; i++) {
            dp1[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp1[j] + 1 > dp1[i]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        dp2[n-1] = 1;
        for(int i = n-2; i >= 0; i--) {
            dp2[i] = 1;
            for(int j = i+1; j < n; j++) {
                if(arr[i] > arr[j] && dp2[j] + 1 > dp2[i]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(dp1[i] + dp2[i] > ans) {
                ans = dp1[i] + dp2[i];
            }
        }
        ans -= 1;
        System.out.println(ans);
    }
}
