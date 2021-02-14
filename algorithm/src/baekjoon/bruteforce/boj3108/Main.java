package baekjoon.bruteforce.boj3108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Rectangle {
    int x1, y1, x2, y2;
    Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static Rectangle[] map = null;
    static boolean[] check = null;
    static int n, ans;

    public static boolean isOverlap(Rectangle a, Rectangle b) {  // 사각형 겹침 여부 판단
        int aLeft, aRight, aTop, aBottom;
        int bLeft, bRight, bTop, bBottom;

        // 아래 조건 중 하나라도 TRUE라면 두 사각형은 절대 겹치지 않는다.
        // 1. A의 오른쪽이 B의 왼쪽보다 더 왼쪽에 있는 경우
        // 2. A의 왼쪽이 B의 오른쪽보다 더 오른쪽에 있는 경우
        // 3. A의 위쪽이 B의 아래쪽보다 더 아래쪽에 있는 경우
        // 4. A의 아래쪽이 B의 위쪽보다 더 위쪽에 있는 경우
        // 여기에 추가로 한 사각형이 다른 사각형 안에 내포된 경우까지 고려해야 함

        // 입력받은 두 점이 정렬 되어있다는 보장이 없으므로 왼, 오, 위, 아래 전부 직접 찾아줘야 한다.
        // find a left, right
        if(a.x1 < a.x2) { aLeft = a.x1; aRight = a.x2; }
        else { aLeft = a.x2;aRight = a.x1; }

        // find a top, bottom
        if(a.y1 < a.y2) { aBottom = a.y1; aTop = a.y2; }
        else { aBottom = a.y2; aTop = a.y1; }

        // find b left, right
        if(b.x1 < b.x2) { bLeft = b.x1; bRight = b.x2; }
        else { bLeft = b.x2; bRight = b.x1; }

        // find b top, bottom
        if(b.y1 < b.y2) { bBottom = b.y1; bTop = b.y2; }
        else { bBottom = b.y2; bTop = b.y1; }

        if(aLeft > bRight || aRight < bLeft || aTop < bBottom || aBottom > bTop ||
                (aLeft < bLeft && aRight > bRight && aTop > bTop && aBottom < bBottom) ||  // a가 b를 내포
                (bLeft < aLeft && bRight > aRight && bTop > aTop && bBottom < aBottom)) {  // b가 a를 내포
            return false;
        }else {
            return true;
        }
    }

    public static void dfs(int idx) {  // 점 단위가 아니라 하나의 사각형을 최소단위로 하여 탐색한다.
        for(int i = 0; i < n; i++) {
            if(!check[i] && isOverlap(map[i], map[idx])) {  // 사각형이 겹치면 이동 가능
                check[i] = true;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new Rectangle[n];
        check = new boolean[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            map[i] = new Rectangle(x1, y1, x2, y2);

            // 시작점에 사각형이 있는 경우
            if((x1 == 0 && (y1 <= 0 && y2 >= 0)) || (x2 == 0 && (y1 <= 0 && y2 >= 0)))
                ans = -1;
        }

        for(int i = 0; i < n; i++) {
            if(!check[i]){
                check[i] = true;
                dfs(i);
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}