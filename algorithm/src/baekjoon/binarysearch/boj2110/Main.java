package baekjoon.binarysearch.boj2110;

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
        int c = Integer.parseInt(st.nextToken());
        int[] houses = new int[n];
        for(int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        long min = 1;
        long max = houses[n-1] - houses[0];
        long mid = (min + max) / 2;

        long ans = 1;
        while(min <= max) {
            long cnt = 1;  // 맨 처음 집은 처음부터 포함
            int previous = houses[0];  // 최근에 공유기를 놓은 집
            for(int i = 1; i < n; i++) {
                int dist = houses[i] - previous;  // 최근에 공유기 놓은 집부터 다음 타겟 집까지의 거리
                if(dist >= mid) {  // 무작정 mid 간격으로 놓는 것이 아니다
                    previous = houses[i];
                    cnt += 1;
                }
            }

            if(cnt >= c) {
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