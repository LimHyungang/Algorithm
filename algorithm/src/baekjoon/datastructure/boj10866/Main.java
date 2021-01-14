package baekjoon.datastructure.boj10866;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        Deque<Integer> deque = new ArrayDeque<>();
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "push_front" :
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back" :
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front" :
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.pollFirst() + "\n");
                    break;
                case "pop_back" :
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.pollLast() + "\n");
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