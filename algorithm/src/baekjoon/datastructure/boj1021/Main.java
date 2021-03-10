package baekjoon.datastructure.boj1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(i);
        }

        int ans = 0;
        for(int i = 0; i < m; i++) {
            int targetIdx = list.indexOf(arr[i]);
            if(targetIdx < list.size() - targetIdx) {  // 오른쪽이동 < 왼쪽 이동
                for(int j = 0; j < targetIdx; j++) {
                    list.add(list.remove(0));
                    ans += 1;
                }
            }else {
                for(int j = 0; j < list.size()-targetIdx; j++) {
                    list.add(0, list.remove(list.size() - 1));  // 마지막 인덱스를 0번에 놓음
                    ans += 1;
                }
            }
            list.remove(0);
        }
        System.out.println(ans);
    }
}