package baekjoon.bruteforce.boj9019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;

    static boolean[] check = null;
    static String[] instructions = null;
    static String ans;

    public static void bfs(int start, int destination) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);
        check[start] = true;

        while(!que.isEmpty()) {
            int now = que.poll();

            int D = 2 * now % 10000;
            int S = (now-1 == -1) ? 9999 : now-1;
            int L = (now % 1000) * 10 + (now / 1000);
            int R = (now / 10) + (now % 10) * 1000;

            if(D == destination) { ans = instructions[now] + "D"; return; }
            if(S == destination) { ans = instructions[now] + "S"; return; }
            if(L == destination) { ans = instructions[now] + "L"; return; }
            if(R == destination) { ans = instructions[now] + "R"; return; }

            if(!check[D]) { que.add(D); check[D] = true; instructions[D] = instructions[now] + "D"; }
            if(!check[S]) { que.add(S); check[S] = true; instructions[S] = instructions[now] + "S"; }
            if(!check[L]) { que.add(L); check[L] = true; instructions[L] = instructions[now] + "L"; }
            if(!check[R]) { que.add(R); check[R] = true; instructions[R] = instructions[now] + "R"; }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            check = new boolean[10000];
            instructions = new String[10000];
            Arrays.fill(instructions, "");

            bfs(from, to);
            System.out.println(ans);
        }
    }
}