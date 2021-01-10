package programmers.pg42628;

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            switch(st.nextToken()) {  // work
                case "I" :  // input
                    int input = Integer.parseInt(st.nextToken());
                    minHeap.add(input);
                    maxHeap.add(input);
                    break;
                case "D" :  // delete
                    if(minHeap.isEmpty()) {
                        continue;
                    }
                    if(st.nextToken().equals("1")) {  // 최대값 삭제
                        int max = maxHeap.poll();
                        minHeap.remove(max);
                    }else {  // 최소값 삭제
                        int min = minHeap.poll();
                        maxHeap.remove(min);
                    }
                    break;
            }
        }

        if(minHeap.isEmpty()) {  // 예외처리
            answer[0] = 0;
            answer[0] = 0;
        }else {
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        return answer;
    }
}