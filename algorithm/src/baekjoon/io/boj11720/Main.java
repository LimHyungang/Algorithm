package baekjoon.io.boj11720;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int sum = 0;
        while(T-- > 0) {
            sum += br.read() - '0';
        }
        bw.write(String.valueOf(sum));

        br.close();
        bw.flush();
        bw.close();
    }
}