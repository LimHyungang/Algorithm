package baekjoon.dfsandbfs.boj15663;

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;
    static boolean[] check = null;
    static int[] permu = null;
    static int[] num = null;
    static int n, m;

    public static void dfs(int dept, int deptNow) {
        if (dept == deptNow) {
            for (int i = 0; i < dept; i++) {
                sb.append(permu[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(check[i] == false && used.contains(num[i]) == false) {
                check[i] = true;
                used.add(num[i]);
                permu[deptNow] = num[i];

                dfs(dept, deptNow + 1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new boolean[n];
        permu = new int[m + 1];
        num = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(check[i] == false && used.contains(num[i]) == false) {
                check[i] = true;
                used.add(num[i]);
                permu[0] = num[i];

                dfs(m, 1);

                check[i] = false;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
