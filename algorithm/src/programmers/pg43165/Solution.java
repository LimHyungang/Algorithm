package programmers.pg43165;

class Solution {
    int answer = 0;

    public void dfs(int dept, int calc, int[] numbers, int target) {
        if (dept == numbers.length) {  // 모든 원소를 반드시 전부 사용해야한다
            if (calc == target) {
                this.answer++;
            }
            return;
        }

        // 배열 내의 순서를 그대로 유지하며 +, - 연산을 이어나간다
        dfs(dept + 1, calc + numbers[dept], numbers, target);
        dfs(dept + 1, calc - numbers[dept], numbers, target);
    }

    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return this.answer;
    }
}