package programmers.pg42860;

import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        ArrayList<Integer> ansList = new ArrayList<>();
        ArrayList<Character> cList = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            cList.add(name.charAt(i));
        }

        // 일단 위아래 이동부터
        int upDown = 0;
        for (int i = 0; i < cList.size(); i++) {
            char ch = cList.get(i);
            int upCnt = ch - 'A';
            int downCnt = 26 - upCnt;
            upDown += Math.min(upCnt, downCnt);
        }

        // 쭉 오른쪽
        int rightAns = 0, lastRightIndex = 0;
        for (int i = cList.size() - 1; i >= 0; i--) {
            if (cList.get(i) != 'A') {
                lastRightIndex = i;
                break;
            }
        }
        rightAns = lastRightIndex;
        ansList.add(upDown + rightAns);

        // 쭉 왼쪽
        int leftAns = 0, lastLeftIndex = 0;
        for (int i = 1; i < cList.size(); i++) {
            if (cList.get(i) != 'A') {
                lastLeftIndex = i;
                break;
            }
        }
        leftAns = cList.size() - lastLeftIndex;
        ansList.add(upDown + leftAns);

        // 오른쪽 가다가 왼쪽
        lastLeftIndex = 0;  // i보다 큰 인덱스 중에서 가장 왼쪽에 있는 A가 아닌 인덱스
        for (int i = 1; i < cList.size(); i++) {  // i : 방향전환점
            int leftRight = i * 2;  // i까지 이동했다 다시 [0]으로 돌아옴
            for (int j = i + 1; j < cList.size(); j++) { // lastLeftIndex 찾기
                if (cList.get(j) != 'A') {
                    lastLeftIndex = j;
                    break;
                }
            }
            leftRight += cList.size() - lastLeftIndex;
            ansList.add(upDown + leftRight);
        }

        // 왼쪽 가다가 오른쪽
        lastRightIndex = 0;
        for (int i = 1; i < cList.size(); i++) {
            int leftRight = (cList.size() - i) * 2;  // i까지 이동했다 다시 [0]으로 돌아옴
            for (int j = i - 1; j >= 1; j--) {
                if (cList.get(j) != 'A') {
                    lastRightIndex = j;
                    break;
                }
            }
            leftRight += lastRightIndex;
            ansList.add(upDown + leftRight);
        }

        answer = Collections.min(ansList);
        return answer;
    }
}
