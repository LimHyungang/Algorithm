package baekjoon.bruteforce.boj1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;
    static ArrayList<String> ans = null;
    static char[] arr = null;
    static boolean[] check = null;
    static int L, C;

    public static boolean isPossible() {
        int consonant = 0, vowel = 0;
        for(int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel += 1;
            }else {
                consonant += 1;
            }
        }
        // 사실 입력받은 문자들은 중복되지 않는 것이 보장되기 때문에 contains()는 검사할 필요 없음
        if(consonant >= 2 && vowel >= 1 && !ans.contains(sb.toString())) {
            return true;
        }else {
            return false;
        }
    }

    public static void dfs(int deptNow, int dept) {
        if(deptNow == dept) {
            if(isPossible()) {
                ans.add(sb.toString());
            }
            return;
        }

        for(int i = 0; i < C; i++) {
            if(!check[i] && sb.charAt(sb.length() - 1) < arr[i]) {
                check[i] = true;
                sb.append(arr[i]);
                dfs(deptNow + 1, dept);
                check[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ans = new ArrayList<>();
        arr = new char[C];
        check = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        for(int i = 0; i < C; i++) {
            check[i] = true;
            sb.append(arr[i]);
            dfs(1, L);
            check[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }

        Collections.sort(ans);
        for(String str : ans) {
            System.out.println(str);
        }
    }
}