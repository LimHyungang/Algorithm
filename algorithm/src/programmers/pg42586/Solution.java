package programmers.pg42586;

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();

        ArrayList<Integer> pList = new ArrayList<Integer>();
        ArrayList<Integer> sList = new ArrayList<Integer>();
        for(int i = 0; i < progresses.length; i++) {
            pList.add(progresses[i]);
            sList.add(speeds[i]);
        }

        while(!pList.isEmpty()) {
            while(pList.get(0) < 100) {
                for(int i = 0; i < pList.size(); i++) {
                    pList.set(i, pList.get(i) + sList.get(i));
                }
            }
            int cnt = 0;
            while(!pList.isEmpty() && pList.get(0) >= 100) {  // 완료되지 않은 작업 있으면 바로 중지
                pList.remove(0);
                sList.remove(0);
                cnt++;
            }
            answer.add(cnt);
        }
        return answer;
    }
}