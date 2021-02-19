package programmers.pg12901;

class Solution {
    public String solution(int a, int b) {
        String answer = "";

        int[] months = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int total = 0;
        for(int i = 0; i < a; i++) {
            total += months[i];
        }
        total += b;
        answer = days[total % 7];

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }
}