package baekjoon.datastructure.boj1991;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringTokenizer st = null;
    static StringBuilder sb = null;
    static char[][] tree = null;

    public static void preorder(int node) {
        sb.append(tree[node][0]);
        if(tree[node][1] != '.') {
            preorder(tree[node][1] - 65);
        }
        if(tree[node][2] != '.') {
            preorder(tree[node][2] - 65);
        }
    }

    public static void inorder(int node) {
        if(tree[node][1] != '.') {
            inorder(tree[node][1] - 65);
        }
        sb.append(tree[node][0]);
        if(tree[node][2] != '.') {
            inorder(tree[node][2] - 65);
        }
    }

    public static void postorder(int node) {
        if(tree[node][1] != '.') {
            postorder(tree[node][1] - 65);
        }
        if(tree[node][2] != '.') {
            postorder(tree[node][2] - 65);
        }
        sb.append(tree[node][0]);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        tree = new char[n][3];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);  // 노드의 문자를 인덱스로 맞춰준다.
            tree[node - 65][0] = node;
            tree[node - 65][1] = st.nextToken().charAt(0);
            tree[node - 65][2] = st.nextToken().charAt(0);
        }

        preorder(0); sb.append("\n");
        inorder(0); sb.append("\n");
        postorder(0); sb.append("\n");

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}