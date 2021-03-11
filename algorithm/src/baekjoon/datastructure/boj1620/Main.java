package baekjoon.datastructure.boj1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> names = new HashMap<>();
        Map<Integer, String> numbers = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            String name = br.readLine();
            names.put(name, i);
            numbers.put(i, name);
        }

        for(int i = 1; i <= m; i++) {
            String q = br.readLine();
            char ch = q.charAt(0);
            boolean isNum = false;

            if('1' <= ch && ch <= '9') {
                isNum = true;
            }
            if(isNum) {
                sb.append(numbers.get(Integer.parseInt(q)) + "\n");
            }else {
                sb.append(names.get(q) + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}