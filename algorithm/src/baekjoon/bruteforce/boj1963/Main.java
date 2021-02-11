package baekjoon.bruteforce.boj1963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int now;
    int time;
    Pair(int now, int time) {
        this.now = now;
        this.time = time;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;

    static int ans;
    static boolean[] check = null;

    public static boolean isPrime(int num) {
        if(num == 2) return true;
        if(num == 0 || num == 1 || num % 2 == 0) return false;
        for(int i = 3; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void bfs(int start, int destination) {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(start, 0));
        check[start - 1000] = true;

        while(!que.isEmpty()) {
            Pair p = que.poll();
            int now = p.now;
            int time = p.time;
            if(now == destination) {
                ans = time;
                return;
            }

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j <= 9; j++) {
                    if(i == 3 && j == 0) continue;  // 천의자리에는 0이 올 수 없음. 반드시 4자리 수

                    int digit = (int)Math.pow(10, i);
                    int temp = now / (int)Math.pow(10, i) % 10;  // temp = now의 i번째 자리의 수
                    int sub = digit * temp;
                    int sum = digit * j;
                    int next = now - sub + sum;

                    if(!check[next - 1000] && isPrime(next)) {
                        que.add(new Pair(next, time + 1));
                        check[next - 1000] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            check = new boolean[9000];  // 0 ~ 8999 == 1000 ~ 9999
            bfs(from, to);
            System.out.println(ans);
        }
    }
}