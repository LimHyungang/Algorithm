package programmers.pg43162;

import java.util.*;

class Solution {
    boolean[] check = null;

    public void dfs(int node, int[][] computers) {
        check[node] = true;

        for(int i = 0; i < computers[node].length; i++) {
            if(computers[node][i] == 1 && check[i] == false) {
                dfs(i, computers);
            }
        }
    }

    public void bfs(int start, int[][] computers) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()) {
            int node = que.poll();
            for(int i = 0; i < computers[node].length; i++) {
                if(computers[node][i] == 1 && check[i] == false) {
                    que.add(i);
                    check[i] = true;
                }
            }
        }
    }


    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[computers.length];

        for(int i = 0; i < computers.length; i++) {
            if(check[i] == false) {
                dfs(i, computers);
                // bfs(i, computers)
                answer++;
            }
        }

        return answer;
    }
}
