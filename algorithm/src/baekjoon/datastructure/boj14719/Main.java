package baekjoon.datastructure.boj14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Block {
    int w, h;
    Block(int w, int h) {
        this.w = w;
        this.h = h;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        Block[] arr = new Block[W];
        int sumOfBlock = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            int h = Integer.parseInt(st.nextToken());
            arr[i] = new Block(i, h);
            sumOfBlock += h;
        }

        // 배열의 시작부터 오름차순 블록만 받아서 저장
        Stack<Block> leftStk = new Stack<>();
        Stack<Block> rightStk = new Stack<>();
        int maxHeight = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].h > maxHeight) {
                maxHeight = arr[i].h;
                leftStk.push(arr[i]);
            }
        }

        // 배열의 끝부터 오름차순 블록만 받아서 저장
        maxHeight = 0;
        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i].h > maxHeight) {
                maxHeight = arr[i].h;
                rightStk.push(arr[i]);
            }
        }

        // 모든 면적 계산
        int ans = (rightStk.peek().w - leftStk.peek().w + 1) * maxHeight;
        int beforeW = leftStk.pop().w;
        while(!leftStk.isEmpty()) {
            int w = leftStk.peek().w;
            int h = leftStk.peek().h;
            ans += (beforeW - w) * h;
            beforeW = w;
            leftStk.pop();
        }
        beforeW = rightStk.pop().w;
        while(!rightStk.isEmpty()) {
            int w = rightStk.peek().w;
            int h = rightStk.peek().h;
            ans += (w - beforeW) * h;
            beforeW = w;
            rightStk.pop();
        }

        // 전체 면적에서 블록 면적 제거
        ans -= sumOfBlock;
        System.out.println(ans);
    }
}