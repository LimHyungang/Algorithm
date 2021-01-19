package baekjoon.dfsandbfs.boj10451;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] map = null;
    static boolean[] check = null;

    public static void dfs(int node) {
        int next = map[node];
        if(!check[next]) {
            check[next] = true;
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            map = new int[n + 1];
            check = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = 0;
            for(int i = 1; i <= n; i++) {
                if(!check[i]) {
                    check[i] = true;
                    dfs(i);
                    cnt += 1;
                }
            }
            System.out.println(cnt);
        }
    }
}