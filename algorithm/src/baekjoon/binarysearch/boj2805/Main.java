package baekjoon.binarysearch.boj2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] trees = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        long min = 1;
        long max = Arrays.stream(trees).max().getAsInt();
        long mid = (min + max) / 2;

        long ans = 0;
        while(min <= max) {
            long sum = 0;
            for(int i = 0; i < n; i++) {
                if(trees[i] > mid) {  // 조건 취하지 않으면 음수가 더해져 답을 해친다
                    sum += trees[i] - mid;
                }
            }
            if(sum >= m) {
                ans = Math.max(ans, mid);
                min = mid + 1;
            }else {
                max = mid - 1;
            }
            mid = (min + max) / 2;
        }
        System.out.println(ans);
    }
}