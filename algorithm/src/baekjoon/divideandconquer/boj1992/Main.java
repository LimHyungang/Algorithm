package baekjoon.divideandconquer.boj1992;

import java.io.*;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringBuilder sb = null;
    static char[] str = null;

    static int[][] map = null;
    static int n;

    public static boolean isPossible(int row, int col, int len) {
        int target = map[row][col];
        for(int i = row; i < row + len; i++) {
            for(int j = col; j < col + len; j++) {
                if(map[i][j] != target) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void divideAndConquer(int row, int col, int len) {
        if(isPossible(row, col, len)) {
            char target = (char)(map[row][col] + '0');  // String.valueOf() 쓰기 싫어서 char로 받음
            sb.append(target);
        }else {
            sb.append("(");
            int nextLen = len / 2;
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    divideAndConquer(row + i*nextLen, col + j*nextLen, nextLen);
                }
            }
            sb.append(")");
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            str = (" " + br.readLine()).toCharArray();
            for(int j = 1; j <= n; j++) {
                map[i][j] = str[j] - '0';
            }
        }

        divideAndConquer(1, 1, n);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}