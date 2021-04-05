package baekjoon.math.boj2108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int n;
    static double[] arr;

    public static int process1() {
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
        }
        return (int)Math.round(sum / n);
    }

    public static int process2() {
        double[] temp = arr.clone();
        Arrays.sort(temp);
        return (int)temp[n/2];
    }

    public static int process3() {
        int max = 0;
        int[] cnt = new int[8001];
        for(int i = 0; i < n; i++) {
            int idx =  (int)arr[i] + 4000;
            cnt[idx] += 1;
            if(max < cnt[idx]) {
                max = cnt[idx];
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] ==  max) {
                list.add(i - 4000);
            }
        }

        if(list.size() == 1) {
            return list.get(0);
        }else {
            Collections.sort(list);
            return list.get(1);
        }
    }

    public static int process4() {
        double[] temp = arr.clone();
        Arrays.sort(temp);
        return (int)(temp[n-1] - temp[0]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new double[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        sb.append(process1() + "\n");
        sb.append(process2() + "\n");
        sb.append(process3() + "\n");
        sb.append(process4() + "\n");
        System.out.println(sb.toString());
    }
}