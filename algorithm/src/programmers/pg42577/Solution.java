package programmers.pg42577;

import java.util.Arrays;
import java.util.Comparator;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean solution(String[] phone_book) {
        // String의 길이순으로 정렬
        Arrays.sort(phone_book, Comparator.comparing(String::length));

        // O(n^2) 시간 소요..
        for(int i = 0; i < phone_book.length - 1; i++) {
            for(int j = i + 1; j < phone_book.length; j++) {
                // charAt()으로 하나씩 검사하지 않고 substring으로 한 번에 처리
                int len = phone_book[i].length();
                String sub = phone_book[j].substring(0, len);
                if (phone_book[i].equals(sub)) {  // 접두어 존재
                    return false;  // 존재'할' 경우에 false 반환임. 헷갈림 주의
                }
            }
        }
        return true;
    }
}
