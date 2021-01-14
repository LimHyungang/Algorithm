package baekjoon.string.boj10808;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();

        int[] arr = new int[26];
        int len = s.length();
        for(int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 97;
            arr[idx] += 1;
        }

        for(int num : arr) {
            sb.append(num + " ");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}