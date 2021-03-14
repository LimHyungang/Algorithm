package baekjoon.samsung.boj14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] arr;
    static char[] operator;
    static boolean[] check;

    public static int calculate(int now, char oper, int dept) {
        switch(oper) {
            case '+' :
                return now += arr[dept];
            case '-' :
                return now -= arr[dept];
            case '*' :
                return now *= arr[dept];
            case '/' :
                return now /= arr[dept];
            default :  // 의미 없음
                return 0;
        }
    }

    public static void dfs(int dept, int calc) {
        if(dept == n) {
            max = Math.max(max, calc);
            min = Math.min(min, calc);
            return;
        }

        Set<Character> set = new HashSet<>();
        for(int i = 1; i < n; i++) {
            if(!check[i] && !set.contains(operator[i])) {
                set.add(operator[i]);
                check[i] = true;
                int next = calculate(calc, operator[i], dept);
                dfs(dept + 1, next);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        operator = new char[n];
        check = new boolean[n];
        int idx = 1;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                switch(i) {
                    case 0:
                        operator[idx++] = '+';
                        break;
                    case 1:
                        operator[idx++] = '-';
                        break;
                    case 2:
                        operator[idx++] = '*';
                        break;
                    case 3:
                        operator[idx++] = '/';
                        break;
                }
            }
        }

        dfs(1, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }
}