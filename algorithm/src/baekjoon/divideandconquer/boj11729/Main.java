package baekjoon.divideandconquer.boj11729;

import java.io.*;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringBuilder sb = null;
    static int cnt;

    public static void hanoi(int n, int from, int to, int temp) {
        if(n == 1) {
            sb.append(from + " " + to + "\n");
            cnt += 1;
        }else {
            hanoi(n-1, from, temp, to);
            sb.append(from + " " + to + "\n");
            cnt += 1;
            hanoi(n-1, temp, to, from);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        hanoi(n, 1, 3, 2);

        System.out.println(cnt);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}