package programmers.pg68644;

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    ArrayList<Integer> answer = new ArrayList<>();

    public void dfs(int idx, int cnt, int sum, int[] numbers) {
        if(cnt == 2) {
            if(!answer.contains(sum)) {
                answer.add(sum);
            }
            return;
        }
        if(idx != numbers.length) {
            dfs(idx + 1, cnt + 1, sum + numbers[idx], numbers);
            dfs(idx + 1, cnt, sum, numbers);
        }
    }

    public ArrayList<Integer> solution(int[] numbers) {
        dfs(0, 0, 0, numbers);
        Collections.sort(answer);
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {2, 1, 3, 4, 1};
        ArrayList<Integer> ans = sol.solution(numbers);
        System.out.println(ans);
    }
}