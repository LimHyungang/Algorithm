package baekjoon.datastructure.boj1158;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= n; i++) {
            que.add(i);
        }

        sb.append("<");
        while(!que.isEmpty()) {
            if(que.size() == 1) {
                sb.append(que.poll());
                break;
            }

            for(int i = 1; i < k; i++) {
                que.add(que.poll());
            }
            sb.append(que.poll() + ", ");
        }
        sb.append(">");

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}