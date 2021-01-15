package baekjoon.datastructure.boj1406;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        char[] arr = br.readLine().toCharArray();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(char c : arr) {
            left.push(c);
        }

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "L" :
                    if(!left.isEmpty())
                        right.push(left.pop());
                    break;
                case "D" :
                    if(!right.isEmpty())
                        left.push(right.pop());
                    break;
                case "B" :
                    if(!left.isEmpty())
                        left.pop();
                    break;
                case "P" :
                    left.push(st.nextToken().charAt(0));
                    break;
            }
        }

        while(!left.isEmpty()) {
            right.push(left.pop());
        }
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}