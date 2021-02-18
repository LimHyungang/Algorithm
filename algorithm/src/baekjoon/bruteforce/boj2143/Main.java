package baekjoon.bruteforce.boj2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static ArrayList<Integer> subA, subB;
    static int[] A, B;
    static int T, N, M;

    public static int upperBound(ArrayList<Integer> list, int target) {
        int min = 0;
        int max = list.size();
        while(min < max) {
            int mid = (min + max) / 2;
            if(list.get(mid) <= target) {
                min = mid + 1;
            }else {
                max = mid;
            }
        }
        return max;
    }

    public static int lowerBound(ArrayList<Integer> list, int target) {
        int min = 0;
        int max = list.size();
        while(min < max) {
            int mid = (min + max) / 2;
            if(list.get(mid) >= target) {
                max = mid;
            }else {
                min = mid + 1;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        // A[] 전처리
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // B[] 전처리
        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 각 배열에 대한 부분배열의 합
        subA = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int sum = A[i];
            subA.add(sum);
            for(int j = i + 1; j < N; j++) {
                sum += A[j];
                subA.add(sum);
            }
            sum = 0;
        }
        Collections.sort(subA);

        subB = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            int sum = B[i];
            subB.add(sum);
            for(int j = i + 1; j < M; j++) {
                sum += B[j];
                subB.add(sum);
            }
            sum = 0;
        }
        Collections.sort(subB);

        // 이분탐색으로 정답 찾기
        long ans = 0;
        for(int i = 0; i < subA.size(); i++) {
            ans += upperBound(subB, T - subA.get(i)) - lowerBound(subB, T - subA.get(i));
        }
        System.out.println(ans);
    }
}