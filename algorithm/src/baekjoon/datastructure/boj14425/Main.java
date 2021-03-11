package baekjoon.datastructure.boj14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            map.put(str, 1);
        }

        int ans = 0;
        for(int i = 0; i < m; i++) {
            String str = br.readLine();
            if(map.get(str) != null) {
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}