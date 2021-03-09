package baekjoon.datastructure.boj1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static int getPriority(char ch) {
        switch(ch) {
            case '*' :
            case '/' :
                return 2;
            case '+' :
            case '-' :
                return 1;
            default :
                return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        Stack<Character> stk = new Stack<>();

        int len = str.length();
        for(int i = 0; i < len; i++) {  // 괄호를 먼저 없앤다
            char ch = str.charAt(i);
            int priority = getPriority(ch);

            switch(ch) {
                case '+' :
                case '-' :
                case '*' :
                case '/' :
                    while(!stk.isEmpty() && getPriority(stk.peek()) >= priority) {
                        sb.append(stk.pop());
                    }
                    stk.push(ch);
                    break;
                case '(' :
                    stk.push(ch);
                    break;
                case ')' :
                    while(!stk.isEmpty() && stk.peek() != '(') {
                        sb.append(stk.pop());
                    }
                    stk.pop();  // '(' 제거
                    break;
                default :  // operand
                    sb.append(ch);
            }
        }

        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(sb.toString());
    }
}