package baekjoon.math.boj1676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cntOfFive = 0;
        cntOfFive += n / 5;
        cntOfFive += n / 25;
        cntOfFive += n / 125;

        System.out.println(cntOfFive);
    }
}
