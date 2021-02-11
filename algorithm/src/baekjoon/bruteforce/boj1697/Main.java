package baekjoon.bruteforce.boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int now;
    int time;
    public Pair(int now, int time) {
        this.now = now;
        this.time = time;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int n, k, ans;
    static boolean[] check = null;

    public static void bfs(int start, int destination) {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(start, 0));
        check[start] = true;

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int now = p.now;
            int time = p.time;

            if(now == destination) {
                ans = time;
                return;
            }

            if(0 <= now+1 && now+1 <= 100000 && !check[now+1]) {
                que.add(new Pair(now+1, time+1));
                check[now+1] = true;
            }
            if(0 <= now-1 && now-1 <= 100000 && !check[now-1]) {
                que.add(new Pair(now-1, time+1));
                check[now-1] = true;
            }
            if(0 <= now*2 && now*2 <= 100000 && !check[now*2]) {
                que.add(new Pair(now*2, time+1));
                check[now*2] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        check = new boolean[100001];

        bfs(n, k);
        System.out.println(ans);
    }
}