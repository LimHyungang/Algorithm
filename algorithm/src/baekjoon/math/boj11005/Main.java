package baekjoon.math.boj11005;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // A ~ Z : 65 ~ 90
        char[] arr = new char[36];
        for(int i = 0; i <= 35; i++) {
            if(i < 10) {
                arr[i] = (char)(i + '0');
            }else {
                arr[i] = (char)(i + 55);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(N != 0) {
            int mod = N % B;
            list.add(mod);
            N = N / B;
        }
        Collections.reverse(list);

        for(int mod : list) {
            sb.append(arr[mod]);
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}