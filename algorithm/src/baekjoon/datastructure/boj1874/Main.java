package baekjoon.datastructure.boj1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int n = Integer.parseInt(br.readLine());

        int lastPush = 0;
        while(n-- > 0) {
            int target = Integer.parseInt(br.readLine());

            if(stk.isEmpty()) {
                int len = target - lastPush;
                for(int i = 0; i < len; i++) {
                    stk.push(++lastPush);
                    sb.append("+").append("\n");
                }
                stk.pop();
                sb.append("-").append("\n");
            }else {
                if(stk.peek() == target) {
                    stk.pop();
                    sb.append("-").append("\n");
                }else if(stk.peek() > target) {
                    System.out.println("NO");
                    return;
                }else if(stk.peek() < target) {
                    int len = target - lastPush;
                    for(int i = 0; i < len; i++) {
                        stk.push(++lastPush);
                        sb.append("+").append("\n");
                    }
                    stk.pop();
                    sb.append("-").append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}