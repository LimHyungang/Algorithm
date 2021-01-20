package baekjoon.dfsandbfs.boj11725;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] map = null;
    static int[] parent = null;
    static boolean[] check = null;

    public static void dfs(int node) {
        for(int next : map[node]) {
            if(!check[next]) {
                check[next] = true;
                parent[next] = node;
                dfs(next);
            }
        }
    }

    public static void bfs(int root) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(root);
        check[root] = true;

        while(!que.isEmpty()) {
            int now = que.poll();
            for(int next : map[now]) {
                if(!check[next]) {
                    check[next] = true;
                    parent[next] = now;
                    que.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        map = new ArrayList[n + 1];
        parent = new int[n + 1];
        check = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u].add(v);
            map[v].add(u);
        }

//        dfs(1); check[1] = true;
        bfs(1);
        for(int i = 2; i <= n; i++) {
            sb.append(parent[i] + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}