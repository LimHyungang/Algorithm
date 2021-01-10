package programmers.pg43105;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        // 초기화
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];  // 왼쪽 빗변 경로
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];  // 오른쪽 빗변 경로
        }

        for(int i = 2; i < triangle.length; i++) {
            for(int j = 1; j < i ; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        // 마지막 행만 검사하면 된다
        for(int i = 0; i < triangle.length; i++) {
            if(answer < dp[triangle.length - 1][i]) {
                answer = dp[triangle.length - 1][i];
            }
        }
        return answer;
    }
}
