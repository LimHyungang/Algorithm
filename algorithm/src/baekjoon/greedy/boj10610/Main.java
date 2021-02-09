package baekjoon.greedy.boj10610;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String n = br.readLine();
        int[] cnt = new int[10];

        long total = 0;
        for(int i = 0; i < n.length(); i++) {
            int temp = n.charAt(i) - '0';
            cnt[temp] += 1;
            total += temp;
        }

        // 30의 배수는 10의 배수이면서 3의 배수이다
        // 10의 배수는 반드시 0을 하나 이상 가져야 한다
        // 3의 배수이려면 모든 자리수의 합이 3의 배수여야 한다
        if(!n.contains("0") || total % 3 != 0) {
            bw.write("-1");
            br.close();
            bw.flush();
            bw.close();
            return;
        }

        // 큰 수부터 그 개수를 찍어준다
        for(int i = 9; i >= 0; i--) {
            for(int j = 0; j < cnt[i]; j++) {
                sb.append(String.valueOf(i));
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}