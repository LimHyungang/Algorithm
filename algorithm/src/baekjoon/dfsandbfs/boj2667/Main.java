package baekjoon.dfsandbfs.boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[][] map = null;
    static boolean[][] check = null;
    static ArrayList<Integer> sizeList = null;

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    static int n = 0;
    static int componentCnt = 0;
    static int componentSize = 0;

    public static void dfs(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(0 <= nr && nr < n && 0 <= nc && nc < n &&
                    map[nr][nc] == 1 && !check[nr][nc]) {
                check[nr][nc] = true;
                dfs(nr, nc);
            }
        }
        componentSize += 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        check = new boolean[n][n];
        sizeList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j = 0; j < n; j++) {
                map[i][j] = ch[j] - '0';
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1 && !check[i][j]) {
                    check[i][j] = true;
                    dfs(i, j);
                    sizeList.add(componentSize);  // 각 구성요소마다 사이즈를 기억
                    componentCnt += 1;
                    componentSize = 0;
                }
            }
        }

        Collections.sort(sizeList);
        System.out.println(componentCnt);
        for(int size : sizeList) {
            System.out.println(size);
        }
    }
}