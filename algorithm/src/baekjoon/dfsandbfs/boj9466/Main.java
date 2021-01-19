package baekjoon.dfsandbfs.boj9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] map = null;
    static int[] check = null;
    static boolean isCycle;
    static int cycleStartNode;
    static ArrayList<Integer> cycleNode = null;

    public static void dfs(int node, int dfsCnt) {
        int next = map[node];
        if(check[next] == 0) {
            check[next] = dfsCnt;
            dfs(next, dfsCnt);
        }else if(check[next] == dfsCnt) {
            isCycle = true;  // cycleStartNode 나올 때까지 true값 유지
            cycleStartNode = next;
        }

        if(isCycle) {
            cycleNode.add(node);
        }
        if(node == cycleStartNode) {  // 사이클에 포함되는 노드 수집 완료
            isCycle = false;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            map = new int[n + 1];
            check = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            int dfsCnt = 1;  // 이미 센 사이클을 또 세지 않기 위함
            cycleNode = new ArrayList<>();
            for(int i = 1; i <= n; i++) {
                if(check[i] == 0) {
                    check[i] = dfsCnt;
                    dfs(i, dfsCnt);
                    dfsCnt += 1;
                }
            }

            System.out.println(n - cycleNode.size());  // 사이클에 포함되지 못한 노드 개수
            cycleStartNode = 0;  // 초기화
        }
    }
}