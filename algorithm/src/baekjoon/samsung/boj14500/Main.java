package baekjoon.samsung.boj14500;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static int[][] map;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};
    static int[][] tetro0, tetro1, tetro2, tetro3, tetro4;

    public static void fillArr() {  // 총 19가지 형태가 나온다
        // 0123 == 상하좌우
        tetro0[0][0] = 3; tetro0[0][1] = 1; tetro0[0][2] = 2;  // 직사각형 이동 경로 1

        tetro1[0][0] = 3; tetro1[0][1] = 3; tetro1[0][2] = 3;  // 직선 이동 경로 1
        tetro1[1][0] = 1; tetro1[1][1] = 1; tetro1[1][2] = 1;  // 직선 이동 경로 2

        tetro2[0][0] = 3; tetro2[0][1] = 1; tetro2[0][2] = 3;  // Z자 이동 경로 1
        tetro2[1][0] = 1; tetro2[1][1] = 2; tetro2[1][2] = 1;  // Z자 이동 경로 2
        tetro2[2][0] = 2; tetro2[2][1] = 1; tetro2[2][2] = 2;  // 대칭 Z자 이동 경로 1
        tetro2[3][0] = 1; tetro2[3][1] = 3; tetro2[3][2] = 1;  // 대칭 Z자 이동 경로 2

        tetro3[0][0] = 1; tetro3[0][1] = 1; tetro3[0][2] = 3;  // ㄴ자 이동 경로 1
        tetro3[1][0] = 0; tetro3[1][1] = 3; tetro3[1][2] = 3;  // ㄴ자 이동 경로 2
        tetro3[2][0] = 3; tetro3[2][1] = 1; tetro3[2][2] = 1;  // ㄴ자 이동 경로 3
        tetro3[3][0] = 1; tetro3[3][1] = 2; tetro3[3][2] = 2;  // ㄴ자 이동 경로 4

        tetro3[4][0] = 1; tetro3[4][1] = 1; tetro3[4][2] = 2;  // 대칭 ㄴ자 이동 경로 1
        tetro3[5][0] = 0; tetro3[5][1] = 2; tetro3[5][2] = 2;  // 대칭 ㄴ자 이동 경로 2
        tetro3[6][0] = 2; tetro3[6][1] = 1; tetro3[6][2] = 1;  // 대칭 ㄴ자 이동 경로 3
        tetro3[7][0] = 1; tetro3[7][1] = 3; tetro3[7][2] = 3;  // 대칭 ㄴ자 이동 경로 4

        tetro4[0][0] = 0; tetro4[0][1] = 2; tetro4[0][2] = 3;  // ㅗ자 이동 경로 1
        tetro4[1][0] = 0; tetro4[1][1] = 3; tetro4[1][2] = 1;  // ㅗ자 이동 경로 2
        tetro4[2][0] = 0; tetro4[2][1] = 2; tetro4[2][2] = 1;  // ㅗ자 이동 경로 3
        tetro4[3][0] = 2; tetro4[3][1] = 3; tetro4[3][2] = 1;  // ㅗ자 이동 경로 4
    }

    public static void search1(int[] tetro) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int r = i, c = j;
                int sum = map[r][c];

                for(int k = 0; k < 3; k++) {
                    r = r + rArr[tetro[k]];
                    c = c + cArr[tetro[k]];
                    if(-1 < r && r < n && -1 < c && c < m) {
                        sum += map[r][c];
                    }else {
                        break;
                    }

                    // 무사히 3번의 이동을 마쳤다면
                    if(k == 2) ans = Math.max(ans, sum);
                }
            }
        }
    }

    // ㅗ자를 위한 search 함수 따로 둔다
    // ㅗ자는 한 번에 이동 불가하고 되돌아와야 함
    // ㅗ자는 이동하는 방식 아닌 그때그떄 다음 좌표만 받아오는 식으로 따로 검사
    public static void search2(int[] tetro) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int r = i, c = j;
                int sum = map[r][c];

                for(int k = 0; k < 3; k++) {
                    int nr = r + rArr[tetro[k]];
                    int nc = c + cArr[tetro[k]];

                    if(-1 < nr && nr < n && -1 < nc && nc < m) {
                        sum += map[nr][nc];
                    }else {
                        break;
                    }

                    // 무사히 3번의 이동을 마쳤다면
                    if(k == 2) ans = Math.max(ans, sum);
                }
            }
        }
    }

    public static void process() {
        search1(tetro0[0]);  // 정사각형
        search1(tetro1[0]); search1(tetro1[1]);  // 직선
        search1(tetro2[0]); search1(tetro2[1]);  // Z자
        search1(tetro2[2]); search1(tetro2[3]);  // Z자 대칭
        search1(tetro3[0]); search1(tetro3[1]); search1(tetro3[2]); search1(tetro3[3]);  // ㄴ자
        search1(tetro3[4]); search1(tetro3[5]); search1(tetro3[6]); search1(tetro3[7]);  // ㄴ자 대칭
        search2(tetro4[0]); search2(tetro4[1]); search2(tetro4[2]); search2(tetro4[3]);  // ㅗ자  // ㅗ자만 search2 사용
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tetro0 = new int[1][3];  // 정사각형
        tetro1 = new int[2][3];  // 직선
        tetro2 = new int[4][3];  // Z자 (+ 대칭)
        tetro3 = new int[8][3];  // ㄴ자 (+ 대칭)
        tetro4 = new int[4][3];  // ㅗ자
        fillArr();

        process();
        System.out.println(ans);
    }
}