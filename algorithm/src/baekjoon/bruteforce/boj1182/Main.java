package baekjoon.bruteforce.boj1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int N, S, ans;
    static int[] arr = null;

    public static void dfs(int dept, int sum) {
        if(dept == N) {
            if(sum == S) {
                ans += 1;
            }
            return;
        }
        dfs(dept + 1, sum + arr[dept]);  // 해당 인덱스를 포함
        dfs(dept + 1, sum);  // 해당 인덱스를 포함하지 않음
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        if(S == 0) {  // S == 0일 경우 공집합도 체크하게 됨. 공집합에 대한 정답 1 제거.
            ans -= 1;
        }
        System.out.println(ans);
    }
}