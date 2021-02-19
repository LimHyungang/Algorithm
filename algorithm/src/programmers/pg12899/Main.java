package programmers.pg12899;

class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int mod = n % 3;
            if(mod == 0) {
                mod = 4;
                n = n / 3 - 1;
            } else
                n /= 3;
            sb.insert(0, String.valueOf(mod));
        }
        return sb.toString();
    }
}

public class Main {
    Solution sol = new Solution();

}
