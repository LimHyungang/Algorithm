package baekjoon.samsung.boj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] gear;
    static int[][] inst;
    static int k;

    public static int calculate() {
        int score = 0;
        if(gear[0][0] == 1) score += 1;
        if(gear[1][0] == 1) score += 2;
        if(gear[2][0] == 1) score += 4;
        if(gear[3][0] == 1) score += 8;
        return score;
    }

    public static int[] spin(int target, int dir) {
        int[] temp = new int[8];
        if(dir == 2) {
            temp[0] = gear[target][7];
            for(int i = 0; i < 7; i++) {
                temp[i+1] = gear[target][i];
            }
        }else {
            temp[7] = gear[target][0];
            for(int i = 1; i < 8; i++) {
                temp[i-1] = gear[target][i];
            }
        }
        return temp;
    }

    public static void process() {
        for(int i = 0; i < k; i++) {
            int target = inst[i][0];
            int dir = inst[i][1];

            // -1 : 회전X, 2 : 시계, 0 : 반시계
            int gear0 = -1, gear1 = -1, gear2 = -1, gear3 = -1;

            // 일단 대상 톱니 주변에 대해서만 처리
            if(target == 0) {
                gear0 = (dir == 1) ? 2 : 0;
                if(gear[0][2] != gear[1][6]) gear1 = 1 - dir;
            }else if(target == 1) {
                gear1 = (dir == 1) ? 2 : 0;
                if(gear[1][6] != gear[0][2]) gear0 = 1 - dir;
                if(gear[1][2] != gear[2][6]) gear2 = 1 - dir;
            }else if(target == 2) {
                gear2 = (dir == 1) ? 2 : 0;
                if(gear[1][2] != gear[2][6]) gear1 = 1 - dir;
                if(gear[2][2] != gear[3][6]) gear3 = 1 - dir;
            }else if(target == 3) {
                gear3 = (dir == 1) ? 2 : 0;
                if(gear[2][2] != gear[3][6]) gear2 = 1 - dir;
            }

            // 연쇄 작용 처리 (왼 -> 오)
            if(gear0 != -1 && gear1 == -1 && gear[0][2] != gear[1][6]) gear1 = (gear0 == 2) ? 0 : 2;  // gear0 <-> gear1
            if(gear1 != -1 && gear0 == -1 && gear[0][2] != gear[1][6]) gear0 = (gear1 == 2) ? 0 : 2;  // gear1 <-> gear0
            if(gear1 != -1 && gear2 == -1 && gear[1][2] != gear[2][6]) gear2 = (gear1 == 2) ? 0 : 2;  // gear1 <-> gear2
            if(gear2 != -1 && gear1 == -1 && gear[1][2] != gear[2][6]) gear1 = (gear2 == 2) ? 0 : 2;  // gear2 <-> gear1
            if(gear2 != -1 && gear3 == -1 && gear[2][2] != gear[3][6]) gear3 = (gear2 == 2) ? 0 : 2;  // gear2 <-> gear3
            if(gear3 != -1 && gear2 == -1 && gear[2][2] != gear[3][6]) gear2 = (gear3 == 2) ? 0 : 2;  // gear3 <-> gear2

            // 연쇄 작용 처리 (오 -> 왼)
            if(gear3 != -1 && gear2 == -1 && gear[2][2] != gear[3][6]) gear2 = (gear3 == 2) ? 0 : 2;  // gear3 <-> gear2
            if(gear2 != -1 && gear3 == -1 && gear[2][2] != gear[3][6]) gear3 = (gear2 == 2) ? 0 : 2;  // gear2 <-> gear3
            if(gear2 != -1 && gear1 == -1 && gear[1][2] != gear[2][6]) gear1 = (gear2 == 2) ? 0 : 2;  // gear2 <-> gear1
            if(gear1 != -1 && gear2 == -1 && gear[1][2] != gear[2][6]) gear2 = (gear1 == 2) ? 0 : 2;  // gear1 <-> gear2
            if(gear1 != -1 && gear0 == -1 && gear[0][2] != gear[1][6]) gear0 = (gear1 == 2) ? 0 : 2;  // gear1 <-> gear0
            if(gear0 != -1 && gear1 == -1 && gear[0][2] != gear[1][6]) gear1 = (gear0 == 2) ? 0 : 2;  // gear0 <-> gear1

            for(int j = 0; j < 4; j++) {
                if(j == 0 && gear0 == -1) continue;
                if(j == 1 && gear1 == -1) continue;
                if(j == 2 && gear2 == -1) continue;
                if(j == 3 && gear3 == -1) continue;

                if(j == 0)  gear[0] = spin(j, gear0);
                if(j == 1)  gear[1] = spin(j, gear1);
                if(j == 2)  gear[2] = spin(j, gear2);
                if(j == 3)  gear[3] = spin(j, gear3);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        gear = new int[4][8];
        for(int i = 0; i < 4; i++) {
            String str = br.readLine();
            for(int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j) - '0';
            }
        }
        k = Integer.parseInt(br.readLine());
        inst = new int[k][2];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            inst[i][0] = Integer.parseInt(st.nextToken()) - 1;
            inst[i][1] = Integer.parseInt(st.nextToken());
        }

        process();
        int ans = calculate();
        System.out.println(ans);
    }
}