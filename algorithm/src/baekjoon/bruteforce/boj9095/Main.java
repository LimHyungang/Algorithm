package baekjoon.bruteforce.boj9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = null;
    static int ans;

    public static void dfs(int sum, int target) {
        if(sum >= target) {
            if(sum == target) {
                ans += 1;
            }
            return;
        }

        for(int i = 1; i <= 3; i++) {
            dfs(sum + i, target);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ans = 0;
            dfs(0, n);
            System.out.println(ans);
        }
    }
}