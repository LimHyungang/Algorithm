package baekjoon.samsung.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 접근 방식
 *   1. 현재 위치에서 먹을 수 있는 생선의 위치를 찾는 bfs
 *     1.1 먹을 수 있는 생선을 찾았다면? -> 그 거리를 넘어가는 탐색은 더 이상 하지 않음
 *       1.1.1 먹을 수 있는 생선들 중 조건에 맞는 생선의 위치로 이동
 *       1.1.2 상어 크기 키울 수 있다면 키움
 *       1.1.3 1번부터 다시 수행
 *
 *     1.2 먹을 수 있는 생선을 찾지 못하고 모든 map을 탐색했다면? -> 그대로 종료
 *
 */

class Pair implements Comparable<Pair> {
    int r, c, dist;
    Pair(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;  // 상어로부터의 거리
    }

    // dist는 검사 기준으로 둘 필요 없다
    // 어차피 한 번의 bfs에서 같은 거리에 존재하는 생선들만 pq에 들어가기 때문
    @Override
    public int compareTo(Pair o) {
        if(this.r != o.r) {
            return (this.r < o.r) ? -1 : 1;
        }else {
            return (this.c < o.c) ? -1 : 1;
        }
    }
}

public class Main {
    static int n, ans, eatCnt, sharkSize = 2;
    static boolean find;  // 먹을 수 있는 생선 찾았는지
    static int[][] map;
    static boolean[][] check;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    static Pair shark;
    static Queue<Pair> que;
    static PriorityQueue<Pair> fishes;  // eatable fish

    public static void bfs(int r, int c) {
        check = new boolean[n][n];
        que = new ArrayDeque<>();
        fishes = new PriorityQueue<>();
        check[r][c] = true;
        que.add(shark);

        int eatableDist = Integer.MAX_VALUE;
        while(!que.isEmpty()) {
            Pair p = que.poll();

            // p.dist + 1 거리에서 먹을 수 있다 하더라도 이미 최소 거리 초과
            // 즉, 더 이상 eatable fish를 찾을 필요 없음
            if(p.dist == eatableDist) break;

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(-1 < nr && nr < n && -1 < nc && nc < n && !check[nr][nc]) {
                    check[nr][nc] = true;

                    if(map[nr][nc] <= sharkSize) {  // 이동 가능
                        que.add(new Pair(nr, nc, p.dist + 1));
                        if(map[nr][nc] != 0 && map[nr][nc] < sharkSize) {  // 먹을 수 있음
                            if(!find) {  // eatableDist가 계속 갱신되는 것을 막기 위한 조건
                                find = true;
                                eatableDist = p.dist + 1;
                            }
                            fishes.add(new Pair(nr, nc, p.dist + 1));
                        }
                    }
                }
            }
        }
    }

    public static void process() {
        while(true) {
            find = false;
            bfs(shark.r, shark.c);

            if(!find) {  // 이동 가능한 모든 칸 이동해도 먹을 수 없음
                break;
            }else {  // 먹을 수 있는 물고기 존재
                Pair p = fishes.peek();
                map[shark.r][shark.c] = 0;
                map[p.r][p.c] = 9;  // 상어 위치 이동. 사실 굳이 계속 9값을 유지할 필요는 없음.
                ans += p.dist;  // 이동 시간 추가

                shark = new Pair(p.r, p.c, 0);
                if(sharkSize == ++eatCnt) {  // 횟수 채웠으면 상어 사이즈 업
                    eatCnt = 0;
                    sharkSize += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Pair(i, j, 0);
                }
            }
        }

        process();
        System.out.println(ans);
    }
}