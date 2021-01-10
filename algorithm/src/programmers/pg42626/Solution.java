package programmers.pg42626;

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int scov : scoville) {
            minHeap.add(scov);
        }

        while(minHeap.peek() < K) {
            int temp = minHeap.poll() + minHeap.poll() * 2;
            minHeap.add(temp);
            answer++;

            if(minHeap.size() == 1 && minHeap.peek() < K) {
                answer = -1;
                break;
            }
        }
        return answer;
    }
}
