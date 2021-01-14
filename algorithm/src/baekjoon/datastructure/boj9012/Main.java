package baekjoon.datastructure.boj9012;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        Stack<String> stk = null;
        String[] arr = null;
        while(T-- > 0) {
            stk = new Stack<>();
            arr = br.readLine().split("");

            for(String s : arr) {
                if(s.equals("(")) stk.push(s);
                else {
                    if(stk.isEmpty()) {
                        stk.push("end");
                        break;
                    }else {
                        stk.pop();
                    }

                }
            }

            if(stk.isEmpty()) sb.append("YES\n");
            else sb.append("NO\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}