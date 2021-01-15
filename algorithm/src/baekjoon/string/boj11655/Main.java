package baekjoon.string.boj11655;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        // a ~ z : 97 ~ 122
        // A ~ Z : 65 ~ 90

        int len = str.length();
        for(int i = 0; i < len; i++) {
            int ch = str.charAt(i);
            if(97 <= ch && ch <= 122) {
                ch += 13;
                if(ch > 122) {
                    ch = 96 + (ch - 122);
                }
            }else if(65 <= ch && ch <= 90) {
                ch += 13;
                if(ch > 90) {
                    ch = 64 + (ch - 90);
                }
            }
            sb.append((char)ch);
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}