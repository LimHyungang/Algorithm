package programmers.pg49189;

import java.util.*;

class Solution {

    ArrayList<Integer>[] list = null;
    boolean[] check = null;
    int[] dist = null;

    public void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        check[start] = true;
        dist[start] = 1;

        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i = 0; i < list[now].size(); i++) {
                int next = list[now].get(i);
                if (check[next] == false) {
                    que.add(next);
                    check[next] = true;
                    dist[next] = dist[now] + 1;
                }
            }
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        // 0번 인덱스는 무시하고 1번부터 사용
        list = new ArrayList[n + 1];
        check = new boolean[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] connect : edge) {
            int u = connect[0];
            int v = connect[1];
            list[u].add(v);
            list[v].add(u);
        }

        bfs(1);

        int max = 0;
        for(int d : dist) if(max < d) max = d;
        for(int d : dist) if(max == d) answer++;

        return answer;
    }
}
