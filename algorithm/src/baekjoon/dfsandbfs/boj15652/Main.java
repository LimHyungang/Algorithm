package baekjoon.dfsandbfs.boj15652;

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;
    static int[] permu = null;
    static int n, m;

    public static boolean isNotDesc(int len) {
        for (int i = 0; i < len; i++) {
            if (permu[i] > permu[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int dept, int deptNow) {
        if (dept == deptNow) {
            for (int i = 0; i < dept; i++) {
                sb.append(permu[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= n; i++) {
            permu[deptNow] = i;
            if (isNotDesc(deptNow)) {
                dfs(dept, deptNow + 1);
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
        permu = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            permu[0] = i;
            dfs(m, 1);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}