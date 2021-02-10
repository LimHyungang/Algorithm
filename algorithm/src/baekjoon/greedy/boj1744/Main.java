package baekjoon.greedy.boj1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        boolean hasZero = false;
        int oneCnt = 0;
        for(int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input > 0) {
                if(input == 1) {  // 1은 곱하면 오리혀 손해이므로 따로 개수를 세서 더해준다
                    oneCnt += 1;
                }else {
                    positive.add(input);
                }
            }else if(input < 0) {
                negative.add(input);
            }else {
                hasZero = true;
            }
        }

        // 양수, 음수 각자 절대값의 크기로 내림차순
        Collections.sort(positive);
        Collections.reverse(positive);
        Collections.sort(negative);

        int ans = oneCnt;
        int size = positive.size();
        for(int i = 0; i < size; i+=2) {  // positive
            if(i == size - 1) {  // positive가 홀수개이면 절대값이 가장 작은 것은 그냥 더해줌
                ans += positive.get(i);
            }else {
                ans += positive.get(i) * positive.get(i+1);
            }
        }

        size = negative.size();
        for(int i = 0; i < size; i+=2) {  // negative
            if(i == size - 1) {  // negative가 홀수개이면 절대값이 가장 작은 음수를 0으로 상쇄시킬 수 있는지 확인
                if(!hasZero) {  // 음수를 상쇄시킬 0이 없으면 그냥 음수 더하기
                    ans += negative.get(i);
                }
            }else {
                ans += negative.get(i) * negative.get(i+1);
            }
        }
        System.out.println(ans);
    }
}