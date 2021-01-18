package baekjoon.dfsandbfs.boj1260;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = null;

    public static void dfs(int node, ArrayList<Integer>[] map, boolean[] check) {
        check[node] = true;
        sb.append(node + " ");

        for(int i = 0; i < map[node].size(); i++) {
            int next = map[node].get(i);
            if(!check[next]) {
                dfs(next, map, check);
            }
        }
    }

    public static void bfs(int start, ArrayList<Integer>[] map, boolean[] check) {
        check[start] = true;
        sb.append(start + " ");

        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);

        while(!que.isEmpty()) {
            int now = que.poll();
            for(int i = 0; i < map[now].size(); i++) {
                int next = map[now].get(i);
                if(!check[next]) {
                    que.add(next);
                    sb.append(next + " ");
                    check[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] map = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        boolean[] check = new boolean[n + 1];
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u].add(v);
            map[v].add(u);
        }

        for(int i = 1; i <= n; i++) {
            Collections.sort(map[i]);
        }

        dfs(start, map, check);
        sb.append("\n");

        check = new boolean[n + 1];  // 초기화
        bfs(start, map, check);

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}