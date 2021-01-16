package baekjoon.math.boj1373;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        if(n.length() % 3 == 1) {
            n = "00" + n;
        }else if(n.length() % 3 == 2) {
            n = "0" + n;
        }

        ArrayList<Integer> binaryList = new ArrayList<>();
        for(int i = n.length() - 1; i >= 0; i--) {
            binaryList.add(n.charAt(i) - '0');
        }

        ArrayList<Integer> octaList = new ArrayList<>();
        for(int i = 0; i < binaryList.size(); i+=3) {
            int octa = binaryList.get(i) * (int)Math.pow(2, 0);
            octa += binaryList.get(i+1) * (int)Math.pow(2, 1);
            octa += binaryList.get(i+2) * (int)Math.pow(2, 2);
            octaList.add(octa) ;
        }
        Collections.reverse(octaList);

        for(int octa : octaList) {
            System.out.print(octa);
        }
    }
}