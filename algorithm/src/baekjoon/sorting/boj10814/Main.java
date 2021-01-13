package baekjoon.sorting.boj10814;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Member implements Comparable<Member> {
    int age;
    String name;
    int join;
    Member(int age, String name, int join) {
        this.age = age;
        this.name = name;
        this.join = join;
    }

    @Override
    public int compareTo(Member o) {
        if(this.age != o.age) {
            if(this.age < o.age) return -1;
            else return 1;
        }else {
            if(this.join < o.join) return -1;
            else return 1;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Member[] arr = new Member[n];

        StringTokenizer st = null;
        int join = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr[i] = new Member(age, name, join++);
        }
        Arrays.sort(arr);

        for(Member m : arr) {
            sb.append(m.age + " " + m.name + "\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}