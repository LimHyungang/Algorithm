package baekjoon.greedy.boj1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting>{
    int begin;
    int end;
    public Meeting(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    public int compareTo(Meeting o) {
        if(this.end != o.end) {
            if(this.end < o.end) {
                return -1;
            }else {
                return 1;
            }
        }else {
            if(this.begin < o.begin) {
                return -1;
            }else {
                return 1;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Meeting> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int begin = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Meeting(begin, end));
        }

        Collections.sort(list);
        int ans = 0;
        int time = 0;
        for(Meeting m : list) {
            if(m.begin >= time) {
                ans += 1;
                time = m.end;
            }
        }
        System.out.println(ans);
    }
}