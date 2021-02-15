package baekjoon.bruteforce.boj1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = null;
    static int[] arr = null;
    static boolean[] check = null;
    static int N, idx, ans;

    public static void sieveOfEratosthenes() {
        for(int i = 2; i <= N; i++) {
            if(!check[i]) {
                arr[idx++] = i;
                for(int j = i*2; j <= N; j+=i) {
                    check[j] = true;
                }
            }
        }
    }

    public static void twoPointer() {
        int L = 0, R = 0;
        int sum = arr[0];

        while(R < idx) {
            if(sum < N) {
                R += 1;
                sum += arr[R];
            }else if(sum == N) {
                ans += 1;
                R += 1;
                sum += arr[R];
            }else if(sum > N) {
                sum -= arr[L];
                L += 1;
                if(L > R) {
                    R = L;
                    sum = arr[L];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        check = new boolean[N + 1];

        sieveOfEratosthenes();
        twoPointer();
        System.out.println(ans);
    }
}