package programmers.pg42885;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int min = 0;

        Arrays.sort(people);

        // 몸무게가 가장 무거운 사람부터 판단한다.
        for(int max = people.length - 1; min <= max; max--) {
            if(people[max] + people[min] > limit)  // 가장 작은 수와 함께 탈 수 없다면 가장 큰 수는 반드시 혼자 타야만 한다
                answer ++;  // 가장 큰 수 먼저 보낸다. min은 그대로 유지하면서 max만 변경한다
            else {
                answer ++;  // 둘을 같이 보낸다
                min ++;
            }
        }

        return answer;
    }
}
