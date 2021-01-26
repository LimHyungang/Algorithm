package baekjoon.greedy.boj2875;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 여
        int m = Integer.parseInt(st.nextToken());  // 남
        int k = Integer.parseInt(st.nextToken());  // 인턴
        int total = n + m;

        int team = 0;
        while(total - 3 >= k) {
            if(n >= 2 && m >= 1) {
                n -= 2;
                m -= 1;
                total -= 3;
                team += 1;
            }else {
                break;
            }
        }
        System.out.println(team);
    }
}