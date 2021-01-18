package baekjoon.math.boj11653;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        if(n == 1) return;
        while(n != 1) {
            for(int i = 2; i <= n; i++) {
                if(n % i == 0) {
                    n /= i;
                    sb.append(i + "\n");
                    break;
                }
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}