package programmers.pg42578;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        // clothes : [{옷이름, 옷종료}, {옷이름, 옷종류}, ... , {옷이름, 옷종류} ]
        int answer = 1;

        Map<String, Integer> hm = new HashMap<String, Integer>();
        for(String[] cloth : clothes) {
            String key = cloth[1];  // 옷 종류를 key로 사용

            if(hm.get(key) == null) {
                hm.put(key, 2);  // 안 입는 경우도 고려하여 2부터 시작
            }else {
                hm.put(key, hm.get(key) + 1);
            }
        }

        for(Integer num : hm.values()) {
            answer *= num;
        }
        answer--;  // 아무것도 안 입는 경우는 없음
        return answer;
    }
}