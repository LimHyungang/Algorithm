package programmers.pg64061;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        int[] topOfCol = new int[n];
        Arrays.fill(topOfCol, n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 각 열마다 인형이 처음 나오는 row 찾는다.
                // 끝까지 topOfCol[idx] == n 이면 비어있는 열
                if(board[i][j] != 0 && topOfCol[j] == n) {
                    topOfCol[j] = i;
                }
            }
        }

        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < moves.length; i++) {
            int col = moves[i] - 1;
            if(topOfCol[col] == n) continue;  // 해당 열에 인형이 없는 경우
            int row = topOfCol[col];

            int doll = board[row][col];
            topOfCol[col] += 1;
            if(stk.isEmpty()) {
                stk.push(doll);
            }else {
                if(stk.peek() == doll) {
                    stk.pop();
                    answer += 2;
                }else {
                    stk.push(doll);
                }
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{0,0,0,0,0}, {0,0,1,0,3}, {0,2,5,0,1}, {4,2,4,4,2}, {3,5,1,3,1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int ans = sol.solution(board, moves);
        System.out.println(ans);
    }
}