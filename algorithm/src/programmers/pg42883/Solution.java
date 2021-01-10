package programmers.pg42883;

class Solution {
    public String solution(String number, int k) {
        // StringBuilder 안 쓰면 시간초과
        StringBuilder sb = new StringBuilder();

        // j의 범위에 1씩 증가하는 i를 더하는 이유는 뽑아야 하는 숫자가 number.length()-k개에서 하나씩 줄어들기 때문
        // 예를들어 length : 7, k : 3 이라면 새로 뽑아야 하는 수는 총 4개
        // 즉, 늦어도 [k] 에서는 첫번째 수를 뽑아야 하나는 것
        // 이는 다시말하면 뒤에서부터 3개(length - k - 1)는 남겨놓고 나머지 범위에서 하나를 뽑아야 한다는 것
        // 숫자를 하나씩 뽑을 때마다 뒤에 남겨놔야 하는 수의 개수도 하나씩 줄어든다.
        // 그래서 1씩 증가하는 i를 더해준다

        int next = 0;
        for(int i = 0; i < number.length() - k; i++) {
            char max = '0';
            for(int j = next; j <= k + i; j++) {
                if(max < number.charAt(j)) {
                    max = number.charAt(j);
                    next = j + 1;  // 다음 자리수를 선택할 범위의 시작점은 이전 자리수를 선택한 바로 다음으로 잡는다
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}