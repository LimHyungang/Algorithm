package baekjoon.binarysearch.boj10816;

//import java.io.*;
//import java.util.StringTokenizer;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//
//        int n = Integer.parseInt(br.readLine());
//        int[] cards = new int[20000001];  // input의 범의 -천만~천만, 음수까지 처리하기 위해 배열 크기 2천만으로
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0; i < n; i++) {
//            int input = Integer.parseInt(st.nextToken());
//            cards[input + 10000000] += 1;
//        }
//
//        int m = Integer.parseInt(br.readLine());
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0; i < m; i++) {
//            int target = Integer.parseInt(st.nextToken());
//            sb.append(cards[target + 10000000] + " ");
//        }
//
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

    public static int lowerBound(int[] arr, int target) {
        int min = 0;
        int max = arr.length;

        while(min < max) {
            int mid = (min + max) / 2;
            if(arr[mid] >= target) {
                max = mid;
            }else {
                min = mid + 1;
            }
        }
        return max;
    }

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

        Arrays.sort(cards);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            int lower = lowerBound(cards, target);
            int upper = upperBound(cards, target);

            int ans = upper - lower;
            sb.append(ans + " ");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}