package baekjoon.dfsandbfs.boj16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;

    static int[][] map = null;
    static int[][] group = null;
    static int[] cntOfGroup = null;
    static boolean[][] check = null;

    static int N, M, dept, groupNum = 1;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void dfs(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];
            if(-1 < nr && nr < N && -1 < nc && nc < M) {
                if(!check[nr][nc] && map[nr][nc] == 0) {
                    check[nr][nc] = true;
                    group[nr][nc] = groupNum;
                    dept += 1;
                    dfs(nr, nc);
                }
            }
        }
    }

    public static void bfs(int r, int c) {
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(r, c));

        while(!que.isEmpty()) {
            Pair p = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];
                if(-1 < nr && nr < N && -1 < nc && nc < M) {
                    if(!check[nr][nc] && map[nr][nc] == 0) {
                        check[nr][nc] = true;
                        group[nr][nc] = groupNum;
                        dept += 1;
                        que.add(new Pair(nr, nc));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];
        check = new boolean[N][M];
        cntOfGroup = new int[N * M + 1];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 탐색 진행하며 이동 가능 경로들 그룹핑. 각 그룹의 원소의 개수 저장.
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!check[i][j] && map[i][j] == 0) {
                    check[i][j] = true;
                    group[i][j] = groupNum;
                    dept = 1;
//                    dfs(i, j);
                    bfs(i, j);
                    cntOfGroup[groupNum] = dept;
                    groupNum += 1;
                }
            }
        }

        sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    sb.append("0");
                }else {  // map[i][j] == 1
                    // 상하좌우 검사하여 인접한 그룹의 그룹번호 모두 담는다
                    Set<Integer> set = new HashSet<>();
                    for(int k = 0; k < 4; k++) {
                        int nr = i + rArr[k];
                        int nc = j + cArr[k];
                        if(-1 < nr && nr < N && -1 < nc && nc < M) {
                            if(group[nr][nc] != 0 && !set.contains(group[nr][nc])) {
                                set.add(group[nr][nc]);
                            }
                        }
                    }

                    int sum = 1;  // 자기 칸까지 포함해야하므로 1로 초기화
                    for(int num : set) {  // 인접한 그룹의 cnt 모두 더함
                        sum += cntOfGroup[num];
                    }
                    sb.append(String.valueOf(sum % 10));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}