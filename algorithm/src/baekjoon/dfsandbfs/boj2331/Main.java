package baekjoon.dfsandbfs.boj2331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static ArrayList<String> list = null;
    static int ans = 0;

    public static void dfs(String num, int p) {
        int nextNum = 0;
        for(int i = 0; i < num.length(); i++) {
            nextNum += Math.pow(num.charAt(i) - '0', p);
        }

        String nextStr = String.valueOf(nextNum);
        if(list.contains(nextStr)) {
            ans = list.indexOf(nextStr);
            return;
        }else {
            list.add(String.valueOf(nextStr));
            dfs(nextStr, p);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        String a = st.nextToken();
        int p = Integer.parseInt(st.nextToken());
        list.add(a);
        dfs(a, p);
        System.out.println(ans);
    }
}