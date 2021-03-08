package baekjoon.datastructure.boj2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair {
    int num, height;
    Pair(int num, int height) {
        this.num = num;
        this.height = height;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Pair> stk = new Stack<>();

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());

            while(!stk.isEmpty()) {
                if(stk.peek().height > height) {
                    sb.append(stk.peek().num + " ");
                    break;
                }
                stk.pop();
            }
            if(stk.isEmpty()) {
                sb.append("0 ");
            }
            stk.push(new Pair(i, height));

        }
        System.out.println(sb.toString());
    }
}