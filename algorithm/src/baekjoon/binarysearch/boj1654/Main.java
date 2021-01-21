package baekjoon.binarysearch.boj1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] lan = new int[k];
        for(int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }

        long min = 1;
        long max = Arrays.stream(lan).max().getAsInt();
        long mid = (min + max) / 2;

        long ans = 0;
        while(min <= max) {
            long sum = 0;
            for(int i = 0; i < k; i++) {
                sum += lan[i] / mid;
            }
            if(sum >= n) {
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