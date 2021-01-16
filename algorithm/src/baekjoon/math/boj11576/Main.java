package baekjoon.math.boj11576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        int[] aArr = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0, decimal = 0;
        for(int i = m - 1; i >= 0; i--) {
            decimal += aArr[idx++] * Math.pow(a, i);
        }

        int[] bArr = new int[20];  // decimal은 2^20 미만으로 보장됨
        idx = 0;
        while(decimal != 0) {
            bArr[idx++] = decimal % b;
            decimal /= b;
        }

        for(int i = idx - 1; i >= 0; i--) {
            System.out.print(bArr[i] + " ");
        }
    }
}