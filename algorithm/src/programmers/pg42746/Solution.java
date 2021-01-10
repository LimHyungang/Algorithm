package programmers.pg42746;

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);  // 주의, String 끼리는 부등호 연산 사용할 수 없음
            }
        });

        // 예외처리, 맨 앞칸이 0이면 00~0의 형태. 즉, 0
        if(arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for(String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }
}