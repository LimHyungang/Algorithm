package programmers.pg60058;

class Solution {
    public boolean isRight(String str) {
        int len = str.length();
        int lCnt = 0, rCnt = 0;
        for(int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if(c == '(') lCnt += 1;
            else rCnt += 1;
            if(rCnt > lCnt) return false;
        }
        // balanced 는 항상 보장되므로 반복 이후에 또 검사할 필요 없음
        return true;
    }

    public String changeBracket(String str) {
        StringBuilder result = new StringBuilder();
        int len = str.length();
        for(int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if(c == '(') result.append(')');
            else result.append('(');
        }
        return result.toString();
    }

    public String recursive(String input) {
        if(input.equals("")) {
            return input;
        }

        StringBuilder w = new StringBuilder();
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        w.append(input);

        // w -> u, v 반으로 가르기
        int lCnt = 0, rCnt = 0;
        int idx = 0;
        for(idx = 0; idx < w.length(); idx++) {
            char c = w.charAt(idx);
            u.append(c);
            if(c == '(') lCnt += 1;
            else rCnt += 1;
            if(lCnt == rCnt) break;  // 최소 단위의 균형잡힌 문자열 u 생성
        }
        for(int i = idx + 1; i < w.length(); i++) {
            char c = w.charAt(i);
            v.append(c);
        }

        if(isRight(u.toString())) {
            String processedV = recursive(v.toString());
            u.append(processedV);
            return u.toString();
        }else {
            String processedV = recursive(v.toString());
            StringBuilder ans = new StringBuilder();
            ans.append('(');
            ans.append(processedV);
            ans.append(')');

            u.deleteCharAt(0);  // 양 끝 제거
            u.deleteCharAt(u.length() - 1);
            ans.append(changeBracket(u.toString()));  // 모든 괄호 바꾼 U 붙이기

            return ans.toString();
        }
    }

    public String solution(String p) {
        String answer = "";
        answer = recursive(p);
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String p = "()))((()";
        String ans = sol.solution(p);
        System.out.println(ans);
    }
}