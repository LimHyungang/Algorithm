package baekjoon.bruteforce.boj2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] arr = null;
    static int N, M, ans;

    public static void twoPointer() {
        int L = 0, R = 0;
        int sum = arr[0];

        while(R < N) {
            if(sum < M) {
                R += 1;
                sum += arr[R];
            }else if(sum == M) {
                ans += 1;
                R += 1;
                sum += arr[R];  // R == N이 될 때 인덱스 오류를 방지하기 위해 arr을 [N+1]로 잡고 [N]은 0으로 둔다.
            }else if(sum > M) {
                sum -= arr[L];
                L += 1;
                if(L > R) {  // L == R 인데 그 수가 M보다 크다면 L > R이 되어버림. 이에 대한 처리.
                    R = L;
                    sum = arr[L];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        twoPointer();
        System.out.println(ans);
    }
}