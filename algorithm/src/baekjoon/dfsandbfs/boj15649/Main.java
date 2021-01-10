package baekjoon.dfsandbfs.boj15649;

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static boolean[] check = null;

    // 한자리씩 사용하기 쉽도록 comb, numbers는 String으로 나타냈다
    public static void dfs(int dept, int deptNow, String comb, String numbers) {
        if(dept == deptNow) {
            System.out.println(comb);
            return;
        }

        for(int i = 0; i < numbers.length(); i++) {
            if(check[i] == false) {
                String temp = comb;
                comb += numbers.charAt(i) + " ";  // 출력 주의. 숫자 사이에 공백 넣어야 함
                check[i] = true;

                dfs(dept, deptNow + 1, comb, numbers);

                // 백트래킹
                check[i] = false;
                comb = temp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        check = new boolean[n];

        String numbers = "";
        for(int i = 1; i <= n; i++) {
            numbers += String.valueOf(i);
        }

        for(int i = 0; i < n; i++) {
            char ch = numbers.charAt(i);
            String comb = String.valueOf(ch) + " ";

            check[i] = true;
            dfs(m, 1, comb, numbers);

            // 백트래킹
            check[i] = false;
        }
    }
}
