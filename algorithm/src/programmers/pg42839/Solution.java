package programmers.pg42839;

import java.util.*;

class Solution {
    ArrayList<Integer> pList = null;
    boolean[] check = null;
    int ans = 0;

    public boolean isPrime(int num) {
        if(num == 2 && !pList.contains(num)) {
            pList.add(num);
            return true;
        }

        if(num == 0 || num == 1 || num % 2 == 0 || pList.contains(num)) {
            return false;
        }

        for(int i = 3; i <= Math.sqrt(num); i+=2) {
            if(num % i == 0) {
                return false;
            }
        }

        pList.add(num);
        return true;
    }

    public void dfs(int dept, int deptNow, String comb, String numbers) {
        if(dept == deptNow) {  // 목표 자리수에 도달했다면 prime 검사 후 return
            int num = Integer.parseInt(comb);
            if(isPrime(num)) {
                this.ans++;
            }
            return;
        }

        // 백트래킹을 통해 모든 조합 구현
        ArrayList<Character> used = new ArrayList<>();
        for(int i = 0; i < numbers.length(); i++) {
            // 다음 숫자가 이번 자리수에서 쓰인 적 있는지 검사
            if(used.contains(numbers.charAt(i))) continue;

            if(check[i] == false) {
                String temp = comb;  // 다시 돌아오기 위해 저장
                comb += String.valueOf(numbers.charAt(i));
                used.add(numbers.charAt(i));  // 이번 자리수에서 사용된 숫자 표시
                check[i] = true;

                dfs(dept, deptNow + 1, comb, numbers);

                // 백트래킹
                comb = temp;
                check[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        int answer = 0;

        pList = new ArrayList<>();
        check = new boolean[numbers.length()];

        // 1~number.length() 모든 자리수의 조합을 구한다
        for(int i = 1; i <= numbers.length(); i++) {
            dfs(i, 0, "", numbers);
        }

        answer = this.ans;
        return answer;
    }
}
