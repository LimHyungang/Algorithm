package baekjoon.math.boj2745;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        ArrayList<Character> cList = new ArrayList<>();
        for(int i = N.length() - 1; i >= 0; i--) {
            cList.add(N.charAt(i));
        }

        int decimal = 0;
        for(int i = 0; i < cList.size(); i++) {
            int num = 0;
            char ch = cList.get(i);
            if('0' <= ch && ch <= '9') {
                num = ch - '0';
            }else {
                num = ch - 55;
            }
            decimal += num * Math.pow(B, i);
        }
        System.out.println(decimal);
    }
}