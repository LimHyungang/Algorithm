package baekjoon.divideandconquer.boj2447;

import java.io.*;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringBuilder sb = null;
    static char[][] arr = null;

    public static void putSpace(int row, int col, int len) {
        for(int i = row; i < row + len; i++ ) {
            for(int j = col; j < col + len; j++) {
                arr[i][j] = ' ';
            }
        }
    }

    public static void divideAndConquer(int row, int col, int len) {  // row, col은 시작행, 시작열
        if(len == 1) {
            arr[row][col] = '*';
            return;
        }

        int nextLen = len / 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) {  // 큰 덩어리의 [2][2]은 공백 출력
                    putSpace(row + i*nextLen, col + j*nextLen, nextLen);
                }else {
                    divideAndConquer(row + i*nextLen, col + j*nextLen, nextLen);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new char[n+1][n+1];
        divideAndConquer(1, 1, n);

        for(int i = 1 ; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}