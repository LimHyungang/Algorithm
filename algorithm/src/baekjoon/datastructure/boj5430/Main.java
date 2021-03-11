package baekjoon.datastructure.boj5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static String process(Deque<Integer> deque, String commands) {
        boolean reverse = false;

        int len = commands.length();
        for(int i = 0; i < len; i++) {
            char ch = commands.charAt(i);
            if(ch == 'R') {
                reverse = !reverse;
            }else {
                if (deque.size() == 0)
                    return "error\n";
                if (reverse)
                    deque.removeLast();
                else
                    deque.removeFirst();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!deque.isEmpty()) {
            sb.append(reverse ? deque.removeLast() : deque.removeFirst());
            if (deque.size() != 0)
                sb.append(",");
        }
        sb.append("]\n");

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());  // 굳이 입력받지 않아도 되는 N
            String str = br.readLine();
            String[] arr = str.substring(1, str.length() - 1).split(",");

            Deque<Integer> deque = new ArrayDeque<>();
            for (String s : arr) {
                if (!s.equals(""))
                    deque.add(Integer.valueOf(s));
            }
            sb.append(process(deque, commands));
        }
        System.out.println(sb.toString());
    }
}