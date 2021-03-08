package baekjoon.datastructure.boj2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = null;
    static StringBuilder sb = null;
    static Stack<Integer> stk = null;
    static Set<String> set = null;

    static String str = null;
    static int[] pair = null;  // 괄호 쌍 서로의 인덱스 저장하는 배열
    static boolean[] check = null;  // 나와 쌍을 맺는 괄호 지울 것인지

    public static void dfs(int deptNow, int dept) {
        if(deptNow == dept) {
            set.add(sb.toString());
            return;
        }

        char ch = str.charAt(deptNow);
        if(ch == '(') {
            check[deptNow] = true;
            dfs(deptNow+1, dept);  // 해당 쌍의 괄호를 지우는 경우
            check[deptNow] = false;
        }

        if(ch == ')' && check[pair[deptNow]]) {  // 나와 쌍인 '('가 지워져있는지 체크
            check[deptNow] = true;  // 사실 이어져있는 괄호 쌍은 하나뿐이라 여기서 굳이 check 건드릴 필요는 없긴 함
            dfs(deptNow+1, dept);
            check[deptNow] = false;
        }else {  // 괄호 안 지우는 경우 or 숫자일 경우
            sb.append(ch);
            dfs(deptNow+1, dept);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        stk = new Stack<>();
        set = new HashSet<>();

        str = br.readLine();
        pair = new int[str.length()];
        check = new boolean[str.length()];

        int len = str.length();
        for(int i = 0; i < len; i++) {  // 자신과 쌍을 이루는 괄호의 인덱스를 갖도록 함
            char ch = str.charAt(i);
            if(ch == '(') {
                stk.push(i);
            }else if(ch == ')') {
                pair[i] = stk.peek();  // 1, 3 인덱스에 '(', ')' 쌍이 위치할 경우
                pair[stk.peek()] = i;  // pair[1] = 3, pair[3] = 1 이렇게
                stk.pop();
            }
        }

        dfs(0, str.length());
        set.remove(str);  // 괄호를 하나도 삭제하지 않은 경우는 제외
        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list);
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }
}