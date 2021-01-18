package baekjoon.dfsandbfs.boj11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static ArrayList<Integer>[] map = null;
    static boolean[] check = null;

    public static void dfs(int node) {
        check[node] = true;
        for(int next : map[node]) {
            if(!check[next]) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n + 1];
        check = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u].add(v);
            map[v].add(u);
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(!check[i]) {
                dfs(i);
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}