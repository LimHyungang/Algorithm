package baekjoon.math.boj2004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // nCm = n! / (m! * (n-m)!)
        // m!, (n-m)!은 분모에 들어가기 때문에 역으로 뺴줘야 한다

        int cntOfTwo = 0, cntOfFive = 0;
        for(long i = 2; i <= n; i*=2) cntOfTwo += n / i;
        for(long i = 2; i <= m; i*=2) cntOfTwo -= m / i;
        for(long i = 2; i <= n-m; i*=2) cntOfTwo -= (n-m) / i;

        for(long i = 5; i <= n; i*=5) cntOfFive += n / i;
        for(long i = 5; i <= m; i*=5) cntOfFive -= m / i;
        for(long i = 5; i <= n-m; i*=5) cntOfFive -= (n-m) / i;

        int ans = Math.min(cntOfTwo, cntOfFive);
        System.out.println(ans);
    }
}