package programmers.pg42627;

import java.util.*;

class Work {
    Integer arrive;
    int require;
    public Work(int arrive, int require) {
        this.arrive = arrive;
        this.require = require;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        // 시간의 흐름을 하나 두자
        // 현재 시간에서 도착한 놈들끼리 SJF를 수행하면 된다.
        // 1. pqArrive에 다 떄려넣는다.
        // 2. 그 중에서 arrive가 time을 넘긴 것만(이미 도착한 것만) pqRequire로 넘긴다.
        // 3. pqRequire 가지고 SJF 수행.
        PriorityQueue<Work> pqArrive = new PriorityQueue<>(new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                return o1.arrive.compareTo(o2.arrive);
                // return o1.arrive < o2.arrive ? -1 : 1;
            }
        });
        PriorityQueue<Work> pqRequire = new PriorityQueue<>(new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                return o1.require < o2.require ? -1 : 1;
            }
        });

        for(int[] job : jobs) {
            pqArrive.add(new Work(job[0], job[1]));
        }

        int time = 0;
        int wait = 0;
        while(!(pqArrive.isEmpty() && pqRequire.isEmpty())) {
            // 도착한 작업 이동
            // !pqArrive.isEmpty() 없으면 에러. 남아있는 작업이 있어야 넘길 수도 있음
            while(!pqArrive.isEmpty() && pqArrive.peek().arrive <= time) {
                pqRequire.add(pqArrive.poll());
            }

            // SJF
            if(pqRequire.isEmpty()) {
                time = pqArrive.peek().arrive;
            } else {
                int arrive = pqRequire.peek().arrive;
                int require = pqRequire.poll().require;
                time += require;
                wait += time - arrive;
            }
        }

        answer = wait / jobs.length;
        return answer;
    }
}
