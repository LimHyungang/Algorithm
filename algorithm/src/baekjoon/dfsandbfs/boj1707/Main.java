package baekjoon.dfsandbfs.boj1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static ArrayList<Integer>[] map = null;
    static int[] check = null;

    public static void dfs(int node, int mark) {
        check[node] = mark;
        for(int next : map[node]) {
            if(check[next] == 0) {
                dfs(next, 3 - mark);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            map = new ArrayList[n + 1];
            check = new int[n + 1];
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

            for(int i = 1; i <= n; i++) {
                if(check[i] == 0) {
                    dfs(i, 1);
                }
            }

            boolean isBipartite = true;
            for(int i = 1; i <= n; i++) {
                for(int node : map[i]) {
                    if(check[i] == check[node]) {
                        isBipartite = false;
                    }
                }
            }
            System.out.println(isBipartite ? "YES" : "NO");
        }
    }
}