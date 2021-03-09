package baekjoon.datastructure.boj4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if(str.equals(".")) break;

            Stack<Character> stk = new Stack<>();
            boolean isValid = true;
            int len = str.length();
            for(int i = 0; i < len; i++) {
                char ch = str.charAt(i);

                // 괄호가 아닌 모든 문자는 무시
                if(ch == '(' || ch == '[') {
                    stk.push(ch);
                }else if(ch == ')' || ch == ']') {
                    if(ch == ')') {
                        if(!stk.isEmpty() && stk.peek() == '(') {
                            stk.pop();
                        }else {
                            isValid = false;
                            break;
                        }
                    }else if(ch == ']') {
                        if(!stk.isEmpty() && stk.peek() == '[') {
                            stk.pop();
                        }else {
                            isValid = false;
                            break;
                        }
                    }
                }
            }

            if(isValid && stk.isEmpty()) sb.append("yes\n");
            else sb.append("no\n");
        }
        System.out.println(sb.toString());
    }
}