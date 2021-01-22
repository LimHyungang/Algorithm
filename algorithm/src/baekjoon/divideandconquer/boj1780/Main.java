package baekjoon.divideandconquer.boj1780;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringBuilder sb = null;
    static StringTokenizer st = null;

    static int[][] paper = null;
    static int[] answer = null;
    static int n;

    public static boolean isPossible(int row, int col, int len) {
        int target = paper[row][col];
        for(int i = row; i < row + len; i++) {
            for(int j = col; j < col + len; j++) {
                if(paper[i][j] != target) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void divideAndConquer(int row, int col, int len) {  // row, col은 시작행, 시작열임
        if(isPossible(row, col, len)) {
            int target = paper[row][col];
            answer[target + 1] += 1;  // -1, 0, 1 을 [0], [1], [2]에 담는다.
        }else {
            int nextLen = len / 3;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    divideAndConquer(row + i*nextLen, col + j*nextLen, nextLen);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        paper = new int[n+1][n+1];  // 0행, 0열은 버린다.
        answer = new int[3];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideAndConquer(1, 1, n);
        for(int ans : answer) {
            System.out.println(ans);
        }
    }
}