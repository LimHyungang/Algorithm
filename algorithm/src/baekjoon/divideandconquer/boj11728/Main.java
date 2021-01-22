package baekjoon.divideandconquer.boj11728;

//import java.io.*;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int[] arr = new int[n + m];
//
//        st = new StringTokenizer(br.readLine());
//        int idx = 0;
//        for(int i = 0; i < n; i++) {
//            arr[idx++] = Integer.parseInt(st.nextToken());
//        }
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0; i < m; i++) {
//            arr[idx++] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(arr);
//        for(int num : arr) {
//            sb.append(num + " ");
//        }
//        bw.write(sb.toString());
//        br.close();
//        bw.flush();
//        bw.close();
//    }
//}

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        st = new StringTokenizer(br.readLine());
        int[] arr2 = new int[m];
        for(int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        int idx1 = 0, idx2 = 0;
        for(int i = 0; i < n+m; i++) {
            if(idx1 == n) {
                sb.append(arr2[idx2++] + " ");
                continue;
            }
            if(idx2 == m) {
                sb.append(arr1[idx1++] + " ");
                continue;
            }

            if(arr1[idx1] <= arr2[idx2]) {
                sb.append(arr1[idx1++] + " ");
            }else {
                sb.append(arr2[idx2++] + " ");
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}