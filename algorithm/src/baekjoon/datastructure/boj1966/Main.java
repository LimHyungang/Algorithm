package baekjoon.datastructure.boj1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Work {
    int order, priority, done;
    Work(int order, int priority) {
        this.order = order;
        this.priority = priority;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            List<Work> list = new LinkedList<>();
            Work target = null;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int targetOrder = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());
                Work work = new Work(i, p);
                list.add(work);
                if(targetOrder == i) {
                    target = work;  // 타겟 객체 참조
                }
            }

            int done = 1;
            while(!list.isEmpty()) {
                int p = list.get(0).priority;
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).priority > p) {
                        list.add(list.get(0));
                        list.remove(0);
                        break;
                    }

                    if(i == list.size() - 1) {  // 나보다 우선되는 work 없다면
                        list.get(0).done = done++;
                        list.remove(0);
                    }
                }
            }
            System.out.println(target.done);
        }
    }
}