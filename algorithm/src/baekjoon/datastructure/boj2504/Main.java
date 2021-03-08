package baekjoon.datastructure.boj2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("");

        int len = arr.length;
        int SL = 0, LL  = 0;  // small left, large left

        Stack<String> stk = new Stack<>();
        for(int i = 0; i < len; i++) {  // () []를 숫자로 먼저 치환
            String str = arr[i];

            if(str.equals("(")) {
                SL += 1;
                stk.push(str);
            }else if(str.equals("[")) {
                LL += 1;
                stk.push(str);
            }else if(str.equals(")")) {
                if(SL == 0 || stk.peek().equals("[")) {
                    System.out.println(0);
                    return;
                }

                if(stk.peek().equals("(")) {
                    SL -= 1;
                    stk.pop();
                    stk.push("2");
                }else {
                    SL -= 1;
                    stk.push(str);
                }
            }else if(str.equals("]")) {
                if(LL == 0 || stk.peek().equals("(")) {
                    System.out.println(0);
                    return;
                }

                if(stk.peek().equals("[")) {
                    LL -= 1;
                    stk.pop();
                    stk.push("3");
                }else {
                    LL -= 1;
                    stk.push(str);
                }
            }
        }

        if(SL != 0 || LL != 0) {
            System.out.println(0);
            return;
        }

        Stack<String> temp = new Stack<>();
        while(!stk.isEmpty()) {  // 맨 밑부터 순서대로 보기 위해 잠시 다른 스택으로 옮김
            temp.push(stk.pop());
        }

        while(!temp.isEmpty()) {  // 올바르지 않은 괄호에 대해서는 이미 모두 처리했으므로 더 이상 볼 필요 없음
            String str = temp.pop();

            if(str.equals("(")) {
                stk.push(str);
            }else if(str.equals("[")) {
                stk.push(str);
            }else if(str.equals(")")) {
                int sum = 0;
                while(!stk.peek().equals("(")) {
                    int num = Integer.parseInt(stk.pop());
                    sum += num;
                }
                stk.pop();  // "(" 제거
                stk.push(String.valueOf(sum * 2));
            }else if(str.equals("]")) {
                int sum = 0;
                while(!stk.peek().equals("[")) {
                    int num = Integer.parseInt(stk.pop());
                    sum += num;
                }
                stk.pop();  // "[" 제거
                stk.push(String.valueOf(sum * 3));
            }else {  // 숫자는 그냥 넣는다
                stk.push(str);
            }
        }

        int ans = 0;
        while(!stk.isEmpty()) {  // 모든 괄호 처리 후 남은 숫자들 더함
            ans += Integer.parseInt(stk.pop());
        }
        System.out.println(ans);
    }
}