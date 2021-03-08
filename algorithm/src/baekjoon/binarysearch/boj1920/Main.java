package baekjoon.binarysearch.boj1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static boolean bSearch(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;

        while(min <= max) {
            int mid = (min + max) / 2;

            if(arr[mid] == target) {
                return true;
            }else if(arr[mid] > target) {
                max = mid - 1;
            }else if(arr[mid] < target) {
                min = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            if(bSearch(arr, target)) {
                sb.append("1\n");
            }else {
                sb.append("0\n");
            }
        }
        System.out.println(sb.toString());
    }
}