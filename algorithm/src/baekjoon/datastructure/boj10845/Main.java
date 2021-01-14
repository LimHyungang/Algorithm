package baekjoon.datastructure.boj10845;


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        StringTokenizer st = null;
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "push" :
                    deque.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" :
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.poll() + "\n");
                    break;
                case "size" :
                    sb.append(deque.size() + "\n");
                    break;
                case "empty" :
                    if(deque.isEmpty()) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "front" :
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.getFirst() + "\n");
                    break;
                case "back" :
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.getLast() + "\n");
                    break;
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}