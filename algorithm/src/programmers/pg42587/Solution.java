package programmers.pg42587;

import java.util.*;

class Work {
    int loc;
    int priority;
    int done;
    public Work(int loc, int priority) {
        this.loc = loc;
        this.priority = priority;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        List<Work> list = new LinkedList<Work>();
        Work target = null;
        for(int i = 0; i < priorities.length; i++) {
            list.add(new Work(i, priorities[i]));
            if(i == location) {  // 타겟 객체 참조
                target = list.get(i);
            }
        }

        // 첫 원소 꺼내서 검사
        // 뒤에 나보다 우선 있으면 넘긴다 (나보다 큰 것만 따지면 된다)

        int cnt = 1;
        while(!list.isEmpty()) {
            int p = list.get(0).priority;
            for(int i = 0; i < list.size(); i++) {
                if(p < list.get(i).priority) {  // 나보다 우선인 놈 있으면
                    list.add(list.remove(0));  // 뒤로 옮기고 다음 원소 검사
                    break;
                }
                if(i == list.size() - 1) {  // 마지막 원소까지 검사 완료했으면
                    list.get(0).done = cnt++;  // 완료 순서 저장
                    list.remove(0);  // 대기목록에서 제거
                }
            }
        }
        answer = target.done;
        return answer;
    }
}
