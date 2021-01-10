package programmers.pg42895;

class Solution {
    int answer = -1;

    public void dfs(int cnt, int calc, int N, int number) {
        if(calc == number) {
            if(answer == -1 || answer > cnt) {  // 정답이 한 번도 들어온 적이 없거나 최소값을 갱신한 경우
                answer = cnt;
            }
            return;
        }

        int temp = N;
        for(int i = 1; i <= 8 - cnt; i++) {  // N이 8개까지만 사용되도록 함
            dfs(cnt + i, calc + temp, N, number);
            dfs(cnt + i, calc - temp, N, number);
            dfs(cnt + i, calc * temp, N, number);
            dfs(cnt + i, calc / temp, N, number);

            temp = temp * 10 + N;  // N을 하나 더 붙임
        }
    }

    public int solution(int N, int number) {
        dfs(0, 0, N, number);
        return this.answer;
    }
}
