package baekjoon.samsung.boj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, k, l, sec;
    static int[][] map;
    static String[][] move;
    static boolean[][] check;  // check[i][j] == (i, j)에 뱀의 몸통이 있는가?
    static Deque<Pair> que = new ArrayDeque<>();

    static int[] rArr = {0, 1, 0, -1};  // 동남서북
    static int[] cArr = {1, 0, -1, 0};

    public static boolean isFinish(int nr, int nc) {
        if(!(-1 < nr && nr < n && -1 < nc && nc < n) || check[nr][nc]) {
            return true;
        }else {
            return false;
        }
    }

    public static void process() {
        int dir = 0;  // 첫 시작은 동쪽
        int moveIdx = 0;
        while(true) {
            sec += 1;

            Pair head = que.getFirst();
            int nr = head.r + rArr[dir];
            int nc = head.c + cArr[dir];

            if(isFinish(nr, nc)) {
                break;
            }

            que.addFirst(new Pair(nr, nc));
            check[nr][nc] = true;
            if(map[nr][nc] != 1) {  // 사과 아닐 경우 꼬리 제거
                Pair tail = que.getLast();
                check[tail.r][tail.c] = false;
                que.removeLast();
            }else {
                map[nr][nc] = 0;
            }

            // 아직 수행할 명령 남아있고 시간이 일치한다면 수행
            if(moveIdx < l && sec == Integer.parseInt(move[moveIdx][0])) {
                String str = move[moveIdx][1];
                moveIdx += 1;
                if(str.equals("D")) {
                    dir += 1;
                    if(dir == 4) dir = 0;
                }else {
                    dir -= 1;
                    if(dir == -1) dir = 3;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];  // map[i][j] == 1 : 사과
        check = new boolean[n][n];

        check[0][0] = true;
        que.addLast(new Pair(0, 0));
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        l = Integer.parseInt(br.readLine());
        move =  new String[l][2];
        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = st.nextToken();
            move[i][1] = st.nextToken();
        }

        process();
        System.out.println(sec);
    }
}