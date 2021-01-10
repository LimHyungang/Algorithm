package baekjoon.io.boj11721;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int idx = 0;
        while(idx < str.length()) {
            for(int i = idx; i < idx + 10; i++) {
                if(i >= str.length()) break;
                sb.append(str.charAt(i));
            }
            sb.append("\n");
            idx += 10;
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
