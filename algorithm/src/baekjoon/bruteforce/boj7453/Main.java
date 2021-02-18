package baekjoon.bruteforce.boj7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] A, B, C, D, AB, CD;
    static int N;

    public static int upperBound(int[] arr, int target) {
        int min = 0;
        int max = arr.length;
        while(min < max) {
            int mid = (min + max) / 2;
            if(arr[mid] <= target) {
                min = mid + 1;
            }else {
                max = mid;
            }
        }
        return max;
    }

    public static int lowerBound(int[] arr, int target) {
        int min = 0;
        int max = arr.length;
        while(min < max) {
            int mid = (min + max) / 2;
            if(CD[mid] >= target) {
                max = mid;
            }else {
                min = mid + 1;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];

        // N
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // N^2
        AB = new int[N * N];
        CD = new int[N * N];
        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N;j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx += 1;
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        // N^2 * 2 * logN
        long ans = 0;
        for(int num : AB) {
            ans += upperBound(CD, -num) - lowerBound(CD, -num);
        }
        System.out.println(ans);
    }
}