package baekjoon.bruteforce.boj10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int n, ans;
    static int[] arr = null;
    static int[] temp = null;
    static boolean[] check = null;

    public static int calculate() {
        int result = 0;
        for(int i = 0; i < n - 1; i++) {
            result += Math.abs(temp[i] - temp[i+1]);
        }
        return result;
    }

    public static void dfs(int deptNow, int dept) {
        if(deptNow == dept) {
            ans = Math.max(ans, calculate());
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!check[i]) {
                temp[deptNow] = arr[i];
                check[i] = true;
                dfs(deptNow + 1, dept);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        temp = new int[n];
        check = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            temp[0] = arr[i];
            check[i] = true;
            dfs(1, n);
            check[i] = false;
        }
        System.out.println(ans);
    }
}