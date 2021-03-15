package baekjoon.samsung.boj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
    int d;  // durability
    boolean flag;  // has robot?
    Pair(int d, boolean flag) {
        this.d = d;
        this.flag = flag;
    }
}

public class Main {
    static ArrayList<Pair> list = new ArrayList<>();
    static int n, k;
    static int zeroCnt, ans;

    public static void process() {
        while(zeroCnt < k) {
            ans += 1;

            // 1. 벨트가 한 칸 회전한다.
            list.add(0, list.remove(2*n-1));
            list.get(n).flag = false;

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            // 만약 이동할 수 없다면 가만히 있는다.
            // 2.1 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            for(int i = n-1; i >= 0; i--) {
                Pair now = list.get(i);
                Pair next = list.get(i+1);
                if(i == n-1) {  // 내려가는 칸 도착
                    now.flag = false;
                    continue;
                }
                if(now.flag && !next.flag && next.d > 0) {
                    now.flag = false;
                    next.flag = true;
                    next.d -= 1;
                    if(next.d == 0) {
                        zeroCnt += 1;
                    }
                }
            }

            // 3. 올라가는 위치에 로봇이 없다면 로봇을 하나 올린다.
            if(!list.get(0).flag && list.get(0).d > 0) {
                list.get(0).flag = true;
                list.get(0).d -= 1;
                if(list.get(0).d == 0) {
                    zeroCnt += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*n; i++) {
            int d = Integer.parseInt(st.nextToken());
            if(d == 0) zeroCnt += 1;
            list.add(new Pair(d, false));
        }

        process();
        System.out.println(ans);
    }
}