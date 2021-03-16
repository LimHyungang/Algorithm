package baekjoon.samsung.boj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r, c, k;
    static int[][] map;
    static int[] inst;
    static int[] dice;
    static int[] rArr = {0, 0, -1, 1};  // 0123 : 동서북남
    static int[] cArr = {1, -1, 0, 0};

    public static void roll(int dir) {
        int tempR = r + rArr[dir];
        int tempC = c + cArr[dir];
        if(!(-1 < tempR && tempR < n && -1 < tempC && tempC < m)) {
            return;
        }else {
            r = tempR;
            c = tempC;
        }

        int[] temp = dice.clone();
        switch(dir) {
            case 0 :  // 동
                temp[0] = dice[0];
                temp[1] = dice[5];
                temp[2] = dice[1];
                temp[3] = dice[2];
                temp[4] = dice[4];
                temp[5] = dice[3];
                dice = temp;
                break;
            case 1 :  // 서
                temp[0] = dice[0];
                temp[1] = dice[2];
                temp[2] = dice[3];
                temp[3] = dice[5];
                temp[4] = dice[4];
                temp[5] = dice[1];
                dice = temp;
                break;
            case 2 :  // 북
                temp[0] = dice[5];
                temp[1] = dice[1];
                temp[2] = dice[0];
                temp[3] = dice[3];
                temp[4] = dice[2];
                temp[5] = dice[4];
                dice = temp;
                break;
            case 3 :  // 남
                temp[0] = dice[2];
                temp[1] = dice[1];
                temp[2] = dice[4];
                temp[3] = dice[3];
                temp[4] = dice[5];
                temp[5] = dice[0];
                dice = temp;
                break;
        }

        if(map[r][c] == 0) {
            map[r][c] = dice[5];
        }else {
            dice[5] = map[r][c];
            map[r][c] = 0;
        }
        System.out.println(dice[2]);
    }

    public static void process() {
        for(int i = 0; i < k; i++) {
            int dir = inst[i];
            roll(dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        inst = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            inst[i] = Integer.parseInt(st.nextToken()) - 1;  // 인덱스 맞추기 위해 -1
        }

        dice = new int[6];
        process();
    }
}