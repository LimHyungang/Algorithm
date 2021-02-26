package programmers.pg68646;

class Solution {
    public int solution(int[] a) {
        int answer = 2;
        int left = a[0], right = a[a.length - 1];

        int[][] ans = new int[a.length][2];  // [i][0], [i][1] 중 하나만 1이면 지울 수 있음을 의미
        for(int i = 1; i <= a.length - 2; i++) {
            if(left > a[i]) {  // 끝점보다 작으면 지울 수 있다
                left = a[i];  // 더 작은 놈이 나왔으니 왼쪽 최소값 변경
                ans[i][0] = 1;
            }
        }
        for(int i = a.length - 2; i >= 1; i--) {
            if(right > a[i]) {  // 끝점보다 작으면 지울 수 있다
                right = a[i];  // 더 작은 놈이 나왔으니 오른쪽 최소값 변경
                ans[i][1] = 1;
            }
        }
        for(int i = 1; i <= a.length - 2; i++) {
            if(ans[i][0] == 1 || ans[i][1] == 1) {
                answer += 1;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        int ans = sol.solution(a);
        System.out.println(ans);
    }
}