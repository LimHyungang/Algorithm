package baekjoon.bruteforce.boj5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int F, S, G, U, D, ans = -1;
    static int[] map = null;
    static boolean[] check = null;

    public static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(S);
        map[S] = 0;
        check[S] = true;

        while(!que.isEmpty()) {
            int now = que.poll();
            int up = now + U;
            int down = now - D;

            if(up == G || down == G) {  // 최단경로 찾으면 굳이 탐색 이어갈 필요 없음
                ans = map[now] + 1;
                return;
            }

            if(up <= F && !check[up]) {
                que.add(up);
                map[up] = map[now] + 1;
                check[up] = true;
            }
            if(1 <= down && !check[down]) {
                que.add(down);
                map[down] = map[now] + 1;
                check[down] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        // F, S, G, U, D
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        if(S == G) {  // 예외처리
            System.out.println(0);
            return;
        }

        map = new int[F + 1];  // map[idx] : idx층에 가기 위한 최소 버튼 수
        check = new boolean[F + 1];

        bfs();
        System.out.println((ans == -1) ? "use the stairs" : ans);
    }
}