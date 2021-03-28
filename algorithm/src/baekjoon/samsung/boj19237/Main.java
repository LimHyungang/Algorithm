package baekjoon.samsung.boj19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 다른 상어의 냄새가 있는 곳으로는 가지 않는다.
 * 즉, 여러 상어가 한 칸에서 만나는 경우는 그 칸이 빈 칸으로 존재했을 경우에만 가능하다.
 *
 * 냄새의 카운트는 그 칸에 도착해서 냄새를 남긴 순간도 포함해서 센다.
 * 내 냄새가 있는 칸으로 이동한 경우에는 그냥 있던 냄새를 덮어버리고 다시 K부터  카운트 시작
 *
 * 결국 두 작업을 반복해서 하는 것.
 * 1. 냄새를 남긴다
 * 2. 이동한다
 *
 * 상어를 담는 배열과 냄새를 담는 배열 두 개를 따로 사용하는 게 좋을 듯
 *
 */

class Smell {
    int num, time;
    Smell(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

class Shark {
    int r, c, num, dir;
    Shark(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
    }
}

public class Main {
    static int n, m, k, sharkNum, ans;
    static Shark[] sharkArr;
    static Smell[][] smell;
    static int[][] map;
    static int[][][] prior;  // 각 상어의 방향에 대한 우선순위  [num][현재방향][우선순위] == 방향
    static int[] rArr = {0, -1, 1, 0, 0};  // 0상하좌우
    static int[] cArr = {0, 0, 0, -1, 1};

    public static void smelling() {
        for(Shark s : sharkArr) {
            if(s == null) continue;
            int r = s.r, c = s.c, num = s.num;
            if(smell[r][c] == null) {  // 빈 칸으로 들어옴
                smell[r][c] = new Smell(num, k);
            }else {  // 내 냄새가 있는 칸으로 들어옴
                smell[r][c].time = k;
            }
        }
    }

    public static void runOutOfSmellTime() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(smell[i][j] != null) {
                    if(smell[i][j].time == 1) {
                        smell[i][j] = null;
                    }else {
                        smell[i][j].time -= 1;
                    }
                }
            }
        }
    }

    public static void moveShark() {
        // 같은 칸에 여러 상어 오면 제거하는 로직도 여기서 수행
        for(Shark s : sharkArr) {
            if(s == null) continue;

            boolean find = false;
            int r = s.r, c = s.c, num = s.num, dir = s.dir;

            // 빈 칸 체크
            for(int i = 1; i <= 4; i++) {
                int nd = prior[num][dir][i];  // 현 상태에서 i번째 우선운위를 갖는 방향
                int nr = r + rArr[nd];
                int nc = c + cArr[nd];

                if(-1 < nr && nr < n && -1 < nc && nc < n) {
                    if(smell[nr][nc] == null) {  // 남의 냄새가 없는 빈 칸
                        // 이미 빈 칸에 다른 상어가 있으면 대결을 해야 함
                        if(map[nr][nc] == 0) {  // 아무 상어도 없음
                            map[r][c] = 0; map[nr][nc] = num;
                            s.r = nr; s.c = nc; s.dir = nd;
                        }else {  // 다른 상어를 만남
                            if(map[nr][nc] < num) {   // 미리 와있던 상어가 더 쏌
                                sharkArr[num] = null;  // 나 죽음
                                sharkNum -= 1;
                                map[r][c] = 0;
                            }else {
                                sharkArr[map[nr][nc]] = null;  // 미리 와있던 상어 죽음
                                sharkNum -= 1;
                                map[r][c] = 0; map[nr][nc] = num;
                                s.r = nr; s.c = nc; s.dir = nd;
                            }
                        }
                        find = true;
                        break;
                    }
                }
            }
            if(find) continue;

            // 빈 칸이 없는 경우 자기 냄새 있는 칸 체크
            for(int i = 1; i <= 4; i++) {
                int nd = prior[num][dir][i];
                int nr = r + rArr[nd];
                int nc = c + cArr[nd];

                if(-1 < nr && nr < n && -1 < nc && nc < n) {
                    // 여기선 대결을 고려할 필요가 없다
                    // 다른 상어의 냄새가 있는 곳으로 가는 경우는 없기 때문
                    if(smell[nr][nc].num == num) {  // 내 냄새인 경우
                        map[r][c] = 0; map[nr][nc] = num;
                        s.r = nr; s.c = nc; s.dir = nd;
                        break;
                    }
                }
            }
        }
    }

    public static void process() {
        while(sharkNum != 1) {
            smelling();  // 냄새 남긴다
            moveShark();  // 다음 칸으로 이동한다
            runOutOfSmellTime();  // smell time을 줄인다
            ans += 1;
            if(ans > 1000) return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharkNum = m;

        sharkArr = new Shark[m+1];
        map = new int[n][n];
        smell = new Smell[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) {
                    sharkArr[map[i][j]] = new Shark(i, j, map[i][j]);  // idx == 상어의 번호
                }
            }
        }

        // 상어들의 처음 방향 일괄 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= m; i++) {
            sharkArr[i].dir = Integer.parseInt(st.nextToken());
        }

        // 상어들의 방향 우선 순위
        prior = new int[m+1][5][5];  // 행 번호를 상어 번호와 맞춰주기 위해 m+1행
        for(int num = 1; num <= m; num++) {
            for(int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= 4; j++) {
                    prior[num][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        process();  // 1001초가 되면 강제 return 하여 -1 출력
        System.out.println(ans > 1000 ? -1 : ans);  // 1000초가 넘어도 다른 상어가 격자에 남아 있으면 -1을 출력한다.
    }
}