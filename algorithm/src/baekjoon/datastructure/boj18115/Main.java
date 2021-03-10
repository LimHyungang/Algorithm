package baekjoon.datastructure.boj18115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] ans = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int num = n;
        int idx1 = 0, idx2 = 1, idx3 = n-1;
        for(int i = 0; i < n; i++) {
            int inst = arr[i];
            if(inst == 1) {
                ans[idx1] = num;  // idx2가 왼쪽으로 이동하는 경우는 없기 때문에 ans[idx1]은 검사 없이 바로 넣어도 됨
                if(ans[idx1 + 1] == 0) idx1 += 1;  // 다음 인덱스가 이전에 수행한 2번 명령에 의해 값이 들어왔을 수도 있음
                else idx1 = idx2 + 1;  // idx2는 조정한 후에 num을 넣기 떄문에 idx2+1 해주는 것이 맞다(이 시점에선 아직 이전 반복에서 놓은 idx2 좌표를 유지하고 있는 것임)
            }else if(inst == 2) {
                if(ans[idx1+1] == 0) idx2 = idx1 + 1;  // 1번 명령 이후 idx1은 새롭게 갱신되었으니 idx1+1만 확인해주면 됨
                else idx2 += 1;  // idx1와 가장 가까운 답이 정해지지 않은 좌표
                ans[idx2] = num;
            }else if(inst == 3) {  // 뒤에서 넣는 건 3번 하나 뿐이므로 인덱스를 하나씩 왼쪽으로 밀기만 하면 됨
                ans[idx3] = num;
                idx3 -= 1;
            }
            num -= 1;
        }

        for(int i = 0; i < n; i++) sb.append(ans[i] + " ");
        System.out.println(sb.toString());
    }
}