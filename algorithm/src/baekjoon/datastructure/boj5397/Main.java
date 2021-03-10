package baekjoon.datastructure.boj5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            String str = br.readLine();

            int len = str.length();
            for(int i = 0; i < len; i++) {
                char ch = str.charAt(i);

                if(ch == '<') {
                    if(!left.isEmpty()) {
                        right.push(left.pop());
                    }
                }else if(ch == '>') {
                    if(!right.isEmpty()) {
                        left.push(right.pop());
                    }
                }else if(ch == '-') {
                    if(!left.isEmpty()) {
                        left.pop();
                    }
                }else {  // 그냥 문자
                    left.push(ch);
                }
            }

            while(!left.isEmpty()) right.push(left.pop());
            while(!right.isEmpty()) sb.append(right.pop());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}