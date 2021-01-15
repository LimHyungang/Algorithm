package baekjoon.string.boj11656;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        int len = str.length();
        String[] subStr = new String[len];
        for(int i = 0; i < len; i++) {
            subStr[i] = str.substring(i, len);
        }
        Arrays.sort(subStr);
        for(String sub : subStr) {
            sb.append(sub).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}