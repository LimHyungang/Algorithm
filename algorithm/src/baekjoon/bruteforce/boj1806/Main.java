package baekjoon.bruteforce.boj1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] arr = null;
    static int N, S, ans = 100001;

    public static void twoPointer() {
        int L = 0, R = 0;
        int sum = arr[0];

        while(R < N) {
            if(sum < S) {
                R += 1;
                sum += arr[R];
            }else if(sum >= S) {
                ans = Math.min(ans, R - L + 1);
                sum -= arr[L];
                L += 1;
                if(L > R) {  // 사실 입력 부분에서 길이 1이 정답인 경우를 미리 체크했으니 이 조건문은 빠져도 상관 없다.
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
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];  // R == N 됐을 때의 에러 방지
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] >= S) {
                System.out.println(1);
                return;
            }
        }

        twoPointer();
        System.out.println(ans == 100001 ? 0 : ans);
    }
}