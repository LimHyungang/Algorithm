package baekjoon.math.boj6588;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // get prime (sieve of eratosthenes)
        int[] prime = new int[1000001];
        boolean[] isNotPrime = new boolean[1000001];
        int cntOfPrime = 0;
        for(int i = 2; i <= 1000000; i++) {
            if(isNotPrime[i] == false) {
                prime[cntOfPrime++] = i;
                for(int j = i + i; j <= 1000000; j+=i) {
                    isNotPrime[j] = true;
                }
            }
        }

        String input = null;
        while(!(input = br.readLine()).equals("0")) {
            int n = Integer.parseInt(input);
            for(int i = 1; i <= cntOfPrime; i++) {
                if(isNotPrime[n - prime[i]] == false) {
                    sb.append(n + " = " + prime[i] + " + " + (n - prime[i]) + "\n");
                    break;
                }
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}