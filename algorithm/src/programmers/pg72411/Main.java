package programmers.pg72411;

import java.util.*;
import java.io.*;

class Solution {
    int max, mapIdx;
    StringBuilder sb;
    Map<String, Integer>[] mapArr;
    boolean[] check;

    public void push() {
        Map<String, Integer> temp = mapArr[mapIdx];
        if(temp.get(sb.toString()) == null) {
            temp.put(sb.toString(), 1);
            max =  Math.max(max, 1);
        }else {
            temp.put(sb.toString(), temp.get(sb.toString()) + 1);
            max = Math.max(max, temp.get(sb.toString()));
        }
    }

    public void dfs(int deptNow, int dept, int idx, String order) {
        if(deptNow == dept) {
            push();  // 해당 조합 추가
            return;
        }

        int len = order.length();
        for(int i = idx; i < len; i++) {
            if(!check[i] && deptNow + (len - idx) >= dept) {
                sb.append(order.charAt(i));
                check[i] = true;

                dfs(deptNow+1, dept, i+1, order);

                sb.deleteCharAt(sb.length() - 1);
                check[i] = false;
            }
        }
    }

    public ArrayList<String> solution(String[] orders, int[] course) {
        // 초기화
        ArrayList<String> answer = new ArrayList<>();
        sb = new StringBuilder();
        mapArr = new HashMap[course.length];
        for(int i = 0; i < course.length; i++) {  // course.length 만큼 Map 가져야 한다
            mapArr[i] =  new HashMap<>();
        }

        // 순서 맞춰주기 위해 정렬
        for(int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }

        // course[i]를 목표 dept로 하는 조합 생성
        for(int i = 0; i < course.length; i++) {
            max = 0;  // max 초기화
            for(int j = 0; j < orders.length; j++) {
                check = new boolean[orders[j].length()];
                dfs(0, course[i], 0, orders[j]);
            }
            mapIdx += 1;

            if(max < 2) continue;  // 최소 두 명 이상에게 주문된 조합이어야 함
            Map<String, Integer> temp = mapArr[i];
            for(String str : temp.keySet()) {
                if(temp.get(str) == max) {
                    answer.add(str);
                }
            }
        }

        Collections.sort(answer);
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        /**
         *
         * ["XYZ", "XWY", "WXA"]	[2,3,4]	["WX", "XY"]
         */
        Solution sol = new Solution();
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};

        ArrayList<String> ans = sol.solution(orders, course);
        for(String str : ans) {
            System.out.println(str);
        }
    }
}