package programmers.pg42583;

import java.util.LinkedList;
import java.util.Queue;

class Truck {
    int location;
    int weight;
    Truck(int location, int weight) {
        this.location = location;
        this.weight = weight;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Truck> tQue = new LinkedList<Truck>();
        Queue<Truck> bQue = new LinkedList<Truck>();
        for(int w : truck_weights) {
            tQue.add(new Truck(0, w));
        }

        int cnt = 0;  // 다리를 통과한 트럭 개수
        int time = 0;
        int currentWeight = 0;
        while(cnt < truck_weights.length) {
            time++;  // 반복 한 번 당 1초

            // 다리 위 트럭 한 칸 씩 이동
            for(Truck t : bQue) {
                t.location++;
            }

            // 다 건넌 트럭 제거
            if(!bQue.isEmpty() && bQue.peek().location > bridge_length) {
                currentWeight -= bQue.poll().weight;
                cnt++;
            }

            if(!tQue.isEmpty() &&  // 더 들어갈 트럭이 있는가?
                    bQue.size() < bridge_length &&  // 다리에 들어갈 자리가 있는가?
                    currentWeight + tQue.peek().weight <= weight) {  // 다리가 무게를 감당할 수 있는가?
                tQue.peek().location++;
                currentWeight += tQue.peek().weight;
                bQue.add(tQue.poll());
            }
        }
        answer = time;
        return answer;
    }
}
