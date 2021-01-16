package baekjoon.math.boj2089;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[100];
        int idx = -1;
        while(n != 1) {
            if(n < 0) {
                if((n*-1) % 2 == 1) {  // 음홀
                    n = n / -2 + 1;
                    arr[++idx] = 1;
                }else {  // 음짝
                    n = n / -2;
                    arr[++idx] = 0;
                }
            }else {
                if(n % 2 == 1) {  // 양홀
                    n = n / -2;
                    arr[++idx] = 1;
                }else {  // 양짝
                    n = n / -2;
                    arr[++idx] = 0;
                }
            }
        }
        arr[++idx] = 1;
        for(int i = idx; i >= 0; i--) {
            System.out.print(arr[i]);
        }
    }
}