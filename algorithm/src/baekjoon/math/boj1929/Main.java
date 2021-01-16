package baekjoon.math.boj1929;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static boolean isPrime(int num) {
        if(num == 2) return true;
        if(num == 0 || num == 1 || num % 2 == 0) return false;
        for(int i = 3; i <= Math.sqrt(num); i+=2) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for(int i = m; i <= n; i++) {
            if(isPrime(i)) sb.append(i + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}