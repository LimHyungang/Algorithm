package baekjoon.datastructure.boj10828;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stk = new Stack<>();
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "push" :
                    stk.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" :
                    if(stk.isEmpty()) sb.append("-1\n");
                    else sb.append(stk.pop() + "\n");
                    break;
                case "size" :
                    sb.append(stk.size() + "\n");
                    break;
                case "empty" :
                    if(stk.isEmpty()) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "top" :
                    if(stk.isEmpty()) sb.append("-1\n");
                    else sb.append(stk.peek() + "\n");
                    break;
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}