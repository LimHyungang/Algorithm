package baekjoon.datastructure.boj2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Pair {
    int order, next;
    Pair(int order, int next) {
        this.order = order;
        this.next = next;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Pair> que = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int next = Integer.parseInt(st.nextToken());
            que.add(new Pair(i, next));
        }

        while(!que.isEmpty()) {
            sb.append(que.getFirst().order + " ");
            int next = que.poll().next;
            if(que.isEmpty()) break;

            if(next > 0) {
                next -= 1;
                for(int i = 0; i < next; i++) {
                    que.addLast(que.pollFirst());
                }
            }else {
                // 왼쪽으로 이동할 때는 next값 변경하면 안 된다
                // que.poll()로 인해 한 칸 오른쪽으로 가버리는 형태가 되기 때문
                for(int i = 0; i < Math.abs(next); i++) {
                    que.addFirst(que.pollLast());
                }
            }
        }
        System.out.println(sb.toString());
    }
}