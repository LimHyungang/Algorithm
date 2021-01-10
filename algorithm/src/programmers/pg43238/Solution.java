package programmers.pg43238;

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = (long)times[times.length - 1] * n;  // n명 모두 최대시간으로 검사
        long max = (long)times[times.length - 1] * n;
        long min = 1;

        while(min <= max) {  // 시간 범위를 점점 좁히다가 엇길리게 되면 답을 찾은 것임
            long mid = (min + max) / 2;

            long sum = 0;
            for(int time : times) {  // 각 심사관별로 mid 시간동안 몇 명을 검사할 수 있는지의 총합
                sum += mid / time;
            }

            if(sum >= n) {
                answer = Math.min(answer, mid);
                max = mid - 1;  // n명 이상 해결이 가능하다 -> 시간을 줄인다
            }else {
                min = mid + 1;  // n명까지 해결하지 못한다 -> 시간을 늘린다
            }
        }

        return answer;
    }
}