package baekjoon.binarysearch.boj10815;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] numbers = new int[m];
        int[] answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // numbers에 있는 m가지 숫자에 대해 이분탐색을 모두 수행
        Arrays.sort(cards);
        for(int i = 0; i < m; i++) {
            int target = numbers[i];
            int min = 0;
            int max = n-1;
            int mid = (min + max) / 2;

            while(min <= max) {
                if(cards[mid] >= target) {
                    if(cards[mid] == target) {
                        answer[i] = 1;
                        break;
                    }
                    max = mid - 1;
                }else {
                    min = mid + 1;
                }
                mid = (min + max) / 2;
            }
        }

        for(int ans : answer) {
            sb.append(ans + " ");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}