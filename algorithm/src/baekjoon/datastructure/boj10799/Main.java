package baekjoon.datastructure.boj10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Integer> stk = new Stack<>();

        int ans = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {  // 쇠막대 추가 or 레이저 생성
                stk.push(i);
            }else {  // empty에서 ')'가 들어올 일은 없으므로 고려하지 않는다
                int top = stk.pop();
                if(i - top == 1) {  // 바로 전에 나온 '('라면 레이저 생성
                    ans += stk.size();
                }else {  // 바로 전 '('이 아니라면 쇠막대기 하나 종료
                    ans += 1;
                }
            }
        }
        System.out.println(ans);
    }
}