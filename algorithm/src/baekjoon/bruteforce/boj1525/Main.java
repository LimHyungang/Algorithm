package baekjoon.bruteforce.boj1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static Map<Integer, Integer> check = null;  // <검사 대상, dept> 방문여부와 dept를 한 번에 검사할 수 있다.

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void bfs(int init) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(init);
        check.put(init, 0);

        while (!que.isEmpty()) {
            int now = que.poll();
            String nowStr = String.valueOf(now);

            int indexOfNine = nowStr.indexOf("9");  // 일차원 배열 상에서 9의 인덱스
            int r = indexOfNine / 3;  // (핵심)
            int c = indexOfNine % 3;  // (핵심)

            for(int i = 0; i < 4; i++) {
                int nr = r + rArr[i];
                int nc = c + cArr[i];
                int move = nr * 3 + nc;  // (핵심) 일차원 배열 상에서 9가 이동할 인덱스

                if(-1 < nr && nr < 3 && -1 < nc && nc < 3) {
                    StringBuilder sb = new StringBuilder(nowStr);
                    char temp = sb.charAt(move);
                    sb.setCharAt(move, '9');
                    sb.setCharAt(indexOfNine, temp);

                    int next = Integer.parseInt(sb.toString());
                    if(check.get(next) == null) {
                        check.put(next, check.get(now) + 1);
                        que.add(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        check = new HashMap<>();

        int init = 0;
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == 0) input = 9;  // 이차원 배열을 하나의 숫자로 표현하기 위해 0을 9로 바꾼다.
                init = (init * 10) + input;  // 0을 그대로 사용해버리면 나중에 인덱스가 꼬일 수 있음.
            }
        }

        bfs(init);
        if(check.get(123456789) == null) {
            System.out.println(-1);
        }else {
            int ans = check.get(123456789);
            System.out.println(ans);
        }
    }
}