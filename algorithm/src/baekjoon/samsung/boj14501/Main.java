package baekjoon.samsung.boj14501;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//    static int n, ans;
//    static int[] T;
//    static int[] P;
//
//    public static void dfs(int dept, int finish, int sum) {
//        if(dept == n+1) {
//            ans = Math.max(ans, sum);
//            return;
//        }
//
//        if(dept >= finish && dept + T[dept] <= n+1) {
//            dfs(dept+1, dept + T[dept], sum + P[dept]);
//        }
//        dfs(dept+1, finish, sum);
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        T = new int[n+1];
//        P = new int[n+1];
//        for(int i = 1; i <= n; i++) {
//            st = new StringTokenizer(br.readLine());
//            T[i] = Integer.parseInt(st.nextToken());
//            P[i] = Integer.parseInt(st.nextToken());
//        }
//
//        dfs(1, 0, 0);
//        System.out.println(ans);
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] T = new int[n+1];
        int[] P = new int[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];  // dp[n] == n번째 날에 "일을 할 때" 벌 수 있는 최고 금액
        dp[0] = 0;
        int ans = dp[0];
        for(int i = 1; i <= n; i++) {
            if(i + T[i] <= n+1) {  // 이걸 선택하면 퇴사 전에 일을 끝낼 수 있는가?
                for(int j = 0; j < i; j++) {
                    if(j + T[j] <= i) {  // j날에 상담한 후 i날에 상담할 수 있는가?
                        dp[i] = Math.max(dp[i], dp[j] + P[i]);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        System.out.println(ans);
    }
}