package baekjoon.string.boj10820;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // a ~ z : 97 ~ 122
        // A ~ Z : 65 ~ 90
        // 0 ~ 9 : 48 ~ 57
        // ' ' : 32

        String str = null;
        while((str = br.readLine()) != null) {
            int lower = 0, upper = 0, number = 0, space = 0;
            for(int i = 0; i < str.length(); i++) {
                int ch = str.charAt(i);
                if(97 <= ch && ch <= 122) {
                    lower += 1;
                }else if(65 <= ch && ch <= 90) {
                    upper += 1;
                }else if(48 <= ch && ch <= 57) {
                    number += 1;
                }else if(ch == 32) {
                    space += 1;
                }
            }
            sb.append(lower + " " + upper + " " + number + " " + space + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}