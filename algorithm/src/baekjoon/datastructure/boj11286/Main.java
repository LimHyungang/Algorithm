package baekjoon.datastructure.boj11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 != abs2) {
                if (abs1 < abs2) return -1;
                else return 1;
            } else {
                if (o1.intValue() < o2.intValue()) return -1;
                else return 1;
            }
        });

//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                int abs1 = Math.abs(o1);
//                int abs2 = Math.abs(o2);
//                if(abs1 != abs2) {
//                    if(abs1 < abs2) return -1;
//                    else return 1;
//                }else {
//                    if(o1.intValue() < o2.intValue()) return -1;
//                    else return 1;
//                }
//            }
//        });

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input != 0) {
                pq.add(input);
            }else {
                if(pq.isEmpty()) {
                    sb.append("0\n");
                }else {
                    sb.append(pq.poll() + "\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}