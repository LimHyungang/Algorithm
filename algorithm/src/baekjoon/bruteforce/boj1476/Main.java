package baekjoon.bruteforce.boj1476;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];  // E, S, M
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());

        int ans = 1;
        int e = 1, s = 1, m = 1;
        while(!(e == arr[0] && s == arr[1] && m == arr[2])) {
            e += 1;
            s += 1;
            m += 1;
            ans += 1;
            if(e == 16) e = 1;
            if(s == 29) s = 1;
            if(m == 20) m = 1;
        }
        System.out.println(ans);
    }
}