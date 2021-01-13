package baekjoon.sorting.boj10825;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Student implements Comparable<Student> {
    String name;
    int kor;
    int eng;
    int math;

    Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    @Override
    public int compareTo(Student o) {
        if(this.kor != o.kor) {
            if(this.kor > o.kor) return -1;
            else return 1;
        }
        if(this.eng != o.eng) {
            if(this.eng < o.eng) return -1;
            else return 1;
        }
        if(this.math != o.math) {
            if(this.math > o.math) return -1;
            else return 1;
        }
        return this.name.compareTo(o.name);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Student[] arr = new Student[n];

        StringTokenizer st = null;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            arr[i] = new Student(name, kor, eng, math);
        }
        Arrays.sort(arr);

        for(Student s : arr) {
            sb.append(s.name).append("\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}