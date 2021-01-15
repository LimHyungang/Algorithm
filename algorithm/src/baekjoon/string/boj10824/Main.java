package baekjoon.string.boj10824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken() + st.nextToken();
        String s2 = st.nextToken() + st.nextToken();
        long num1 = Long.parseLong(s1);
        long num2 = Long.parseLong(s2);
        System.out.println(num1 + num2);
    }
}