package programmers.pg68645;


class Solution {
    public int[] solution(int n) {
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max += i;
        }
        int[] answer = new int[max];
        int[][] arr = new int[n][n];

        int row = 0, col = 0, input = 1;
        arr[row][col] = input;
        while(input < max) {
            // 상 -> 하 (row++)
            while(row + 1 < n && arr[row+1][col] == 0) {
                arr[++row][col] = ++input;
            }
            // 좌 -> 우 (col++)
            while(col + 1 < n && arr[row][col+1] == 0) {
                arr[row][++col] = ++input;
            }
            // 우하 -> 좌상 (row--, col--)
            while(row - 1 > 0 && col - 1 > 0 && arr[row-1][col-1] == 0) {
                arr[--row][--col] = ++input;
            }
        }

        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }
}