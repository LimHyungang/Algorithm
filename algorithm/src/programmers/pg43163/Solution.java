package programmers.pg43163;

import java.util.*;

class Solution {
    int answer = 51;
    boolean[] check = null;

    public boolean isPossible(String from, String to) {
        int cnt = 0, len = from.length();
        for(int i = 0; i < len; i++) {
            if(from.charAt(i) == to.charAt(i)) {
                cnt++;
            }
        }
        return cnt == len - 1 ? true : false;
    }

    public void dfs(int dept, String now, String target, String[] words) {
        if(now.equals(target)) {
            answer = Math.min(answer, dept);
            return;
        }

        for(int i = 0; i < words.length; i++) {
            String next = words[i];
            if(check[i] == false && isPossible(now, next)) {
                check[i] = true;
                dfs(dept + 1, next, target, words);
                check[i] = false;
            }
        }
    }

    public int solution(String begin, String target, String[] words) {
        check = new boolean[words.length];

        for(int i = 0; i < words.length; i++) {
            String next = words[i];
            if(check[i] == false && isPossible(begin, words[i])) {
                check[i] = true;
                dfs(1, next, target, words);
                check[i] = false;
            }
        }

        return answer == 51 ? 0 : answer;
    }
}
