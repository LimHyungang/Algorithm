package programmers.pg62048;

class Solution {
    public int gcd(int a, int b) {
        if(b == 0) return a;
        else return gcd(b, a % b);
    }

    public long solution(int w, int h) {
        long answer = 0;
        int gcd = gcd(w, h);
        answer = ((long)w * (long)h) - (((long)w / gcd) + ((long)h / gcd) - 1) * gcd;
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }
}