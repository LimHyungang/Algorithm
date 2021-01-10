package programmers.pg43164;

import java.util.*;

class Solution {
    boolean[] check = null;
    String[] answer = null;
    ArrayList<String> temp = null;

    public void dfs(int dept, String now, String[][] tickets) {
        if(dept == tickets.length + 1) {
            if(answer[0] == null) {
                answer = temp.toArray(new String[tickets.length + 1]);
                return;
            }

            for(int i = 0; i < tickets.length + 1; i++) {
                if(answer[i].compareTo(temp.get(i)) == 0) {
                    continue;
                }else if(answer[i].compareTo(temp.get(i)) < 0) {
                    return;
                }else if(answer[i].compareTo(temp.get(i)) > 0) {
                    answer = temp.toArray(new String[tickets.length + 1]);
                    return;
                }
            }
        }

        for(int i = 0; i < tickets.length; i++) {
            if(check[i] == false && now.equals(tickets[i][0])) {
                check[i] = true;
                temp.set(dept, tickets[i][1]);
                dfs(dept + 1, tickets[i][1], tickets);
                check[i] = false;
            }
        }
    }

    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length + 1];
        check = new boolean[tickets.length];
        temp = new ArrayList<>();

        // add 없이 set 하면 에러남
        for(int i = 0; i < tickets.length + 1; i++) {
            temp.add("");
        }

        // ICN 에서만 출발 가능
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                check[i] = true;
                temp.set(0, tickets[i][0]);
                temp.set(1, tickets[i][1]);
                dfs(2, tickets[i][1], tickets);
                check[i] = false;
            }
        }

        return answer;
    }
}