package programmers.pg42748;

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int ansIdx = 0;
        for(int[] command : commands) {
            int begin = command[0];
            int end = command[1];
            int k = command[2];

            int[] temp = new int[end - begin + 1];
            int idx = 0;
            for(int i = begin - 1; i <= end - 1; i++) {
                temp[idx++] = array[i];
            }
            Arrays.sort(temp);
            answer[ansIdx++] = temp[k - 1];
        }

        return answer;
    }
}
