package baekjoon.dfsandbfs.boj2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] map = null;
    static int[] check = null;
    static ArrayList<Integer> cycleNodeList = null;
    static boolean isCycle;
    static int cycleStartNode;

    public static void dfs(int node) {
        int next = map[node];
        if(check[next] == 0) {
            check[next] = check[node];
            dfs(next);
        }else if(check[next] == check[node]) {
            isCycle = true;
            cycleStartNode = next;
        }

        if(isCycle) {
            cycleNodeList.add(node);
        }
        if(node == cycleStartNode) {
            isCycle = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n + 1];
        check = new int[n + 1];
        cycleNodeList = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        int dfsCnt = 1;
        for(int i = 1; i <= n; i++) {
            if(check[i] == 0) {
                check[i] = dfsCnt;
                dfs(i);
                dfsCnt += 1;
            }
        }

        Collections.sort(cycleNodeList);
        System.out.println(cycleNodeList.size());
        for(int node : cycleNodeList) {
            System.out.println(node);
        }
    }
}