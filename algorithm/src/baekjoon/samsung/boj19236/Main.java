package baekjoon.samsung.boj19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
    int num, dir;
    Fish(int num, int dir) {
        this.num = num;
        this.dir = dir;
    }
}

public class Main {
    static int ans;
    static Fish[][] map;
    static boolean[] check;  // true == 잡아먹힌 샏선
    static int[] rArr = {-1, -1, 0, 1, 1, 1, 0, -1};  // 12시 방향부터 45도씩 반시계 회전
    static int[] cArr = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void swap(int r, int c, int nr, int nc) {
        Fish temp = map[r][c];
        map[r][c] = map[nr][nc];
        map[nr][nc] = temp;
    }

    public static boolean isMovable(int nr, int nc) {
        if(-1 < nr && nr < 4 && -1 < nc && nc < 4) {
            if(map[nr][nc] == null || map[nr][nc].num != 0) {  // 빈칸이거나 상어가 아니거나
                return true;
            }
        }
        return false;
    }

    public static boolean isEatable(int r, int c, int dir) {
        for(int i = 1; i < 4; i++) {  // 최대 3칸 이동 가능
            int nr = r + rArr[dir] * i;
            int nc = c + cArr[dir] * i;
            if(-1 < nr && nr < 4 && -1 < nc && nc < 4) {
                if(map[nr][nc] != null) {  // 물고기가 있는 칸이 하나라도 있으면 eatable
                    return true;
                }
            }else {
                return false;
            }
        }
        return false;
    }

    public static void moveFish() {
        for(int fishNum = 1; fishNum <= 16; fishNum++) {
            if(!check[fishNum]) {  // 아직 먹히지 않은 생선만 이동 가능
                boolean find = false;
                Fish fish = null;

                // 일단 map에서 대상 fish 찾는다
                int r = 0, c = 0;
                for(int i = 0; i < 4; i++) {
                    for(int j = 0; j < 4; j++) {
                        if(map[i][j] != null && map[i][j].num == fishNum) {  // 찾았다
                            find = true;
                            fish = map[i][j];
                            r = i;
                            c = j;
                            break;
                        }
                    }
                    if(find) break;
                }

                // 이동 가능한 칸을 찾아 최대 7번 회전
                int dir = fish.dir;  // 최초 방향 기억
                for(int i = 0; i < 8; i++) {
                    fish.dir = (dir + i) % 8;  // 회전한 방향으로 이동이 불가해도 방향은 유지한다
                    int nr = r + rArr[fish.dir];
                    int nc = c + cArr[fish.dir];

                    if(isMovable(nr, nc)) {
                        swap(r, c, nr, nc);
                        break;
                    }
                }
            }
        }
    }

    public static Fish[][] deepCopyArr(Fish[][] arr) {
        Fish[][] temp = new Fish[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(arr[i][j] == null) {
                    temp[i][j] = null;
                }else {
                    temp[i][j] = new Fish(arr[i][j].num, arr[i][j].dir);
                }
            }
        }
        return temp;
    }

    public static void dfs(int r, int c, int dir, int sum) {
        moveFish();  // 생선 이동

        if(!isEatable(r, c, dir)) {  // 더 이상 먹을 수 없게 되면 최대값 갱신
            ans = Math.max(ans, sum);
            return;
        }

        Fish[][] tempArr = deepCopyArr(map);  // 현재 map 상태 기억
        for(int i = 1; i < 4; i++) {  // 최대 3칸 이동 가능
            int nr = r + rArr[dir] * i;
            int nc = c + cArr[dir] * i;

            if(-1 < nr && nr < 4 && -1 < nc && nc < 4) {
                if(map[nr][nc] != null) {  // 다음 칸에 물고기 있는지?
                    int num = map[nr][nc].num;
                    int nd = map[nr][nc].dir;
                    check[num] = true;
                    swap(r, c, nr, nc);  // 상어와 물고기의 swap으로 잡아먹기 표현
                    map[r][c] = null;  // 잡아먹힌 물고기 제거
                    map[nr][nc].dir = nd;  // 잡아먹은 물고기의 방향을 이어받는다

                    dfs(nr, nc, nd, sum + num);

                    check[num] = false;
                    map = deepCopyArr(tempArr);
                }
            }else {
                break;  // 범위 벗어났으면 더 검사할 필요 없음
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        map = new Fish[4][4];
        check = new boolean[17];
        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;  // 방향은 1~8로 주어지므로 -1 해줘야 함
                map[i][j] = new Fish(num, dir);
            }
        }

        // [0][0] 물고기 잡아먹으면서 시작
        int num = map[0][0].num;
        int dir = map[0][0].dir;
        check[num] = true;
        map[0][0].num = 0;  // 상어가 들어감
        dfs(0, 0, dir, num);
        System.out.println(ans);
    }
}