package programmers.pg42862;

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        // 일단 정렬
        List<Integer> lostList = new ArrayList<>();
        List<Integer> reserveList = new ArrayList<>();
        for (int num : lost) lostList.add(num);
        for (int num : reserve) reserveList.add(num);
        Collections.sort(lostList);
        Collections.sort(reserveList);

        // 여벌을 가져왔어도 도난당했다면 빌려줄 수 없다
        for (int i = 0; i < reserveList.size(); i++) {
            int num = reserveList.get(i);
            if (lostList.contains(num)) {
                lostList.remove((Integer) num);
                reserveList.remove((Integer) num);
                i--;
            }
        }

        // 작은 번호에게 우선적으로 빌려준다
        for (int num : reserveList) {
            if (lostList.contains(num - 1)) {
                lostList.remove((Integer)(num - 1));
                continue;
            }
            if (lostList.contains(num + 1)) {
                lostList.remove((Integer)(num + 1));
                continue;
            }
        }
        answer = n - lostList.size();
        return answer;
    }
}
