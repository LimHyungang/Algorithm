package programmers.pg42567;

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> hm = new HashMap<String, Integer>();

        // 참가자 수만큼 value 증가
        for(String p : participant) {
            // 동명이인을 고려해야한다
            if(hm.get(p) == null) {
                hm.put(p, 1);
            }else {
                hm.put(p, hm.get(p) + 1);
            }
        }

        // 완주자 수만큼 value 감소
        for(String c : completion) {
            hm.put(c, hm.get(c) - 1);
        }

        // value가 1인 key가 정답
        for(String key : hm.keySet()) {
            if(hm.get(key) == 1) {
                answer = key;
                break;
            }
        }
        return answer;
    }
}
