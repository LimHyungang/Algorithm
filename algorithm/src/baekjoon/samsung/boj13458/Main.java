package baekjoon.samsung.boj13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean flag = true;  // true : 총 > 부, false : 부 > 총
        long ans = 0;
        for(int i = 0; i < n; i++) {
            int num = A[i];
            if(flag) {  // 총감1 + 부감
                num -= B;
                ans += 1;  // 총감 1명
                if(num > 0) {  // 총감으로 해결 안 되면
                    if(num % C == 0) {
                        ans += num / C;
                    }else {
                        ans += (num / C) + 1;
                    }
                }
            }else {  // 전부 부감
                if(num % C == 0) {
                    ans += num / C;
                }else {
                    ans += (num / C) + 1;
                }
            }
        }
        System.out.println(ans);
    }
}