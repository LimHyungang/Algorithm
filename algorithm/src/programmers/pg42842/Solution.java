package programmers.pg42842;

class Solution {
    public int[] solution(int brown, int yellow) {
        // y는 최소 1칸 -> h는 최소 3이상
        // b = 2w + 2h - 4
        // w + h = (b + 4) / 2  -> h < (b + 4) / 2
        // y = (w - 2) * (h - 2)

        int[] answer = new int[2];
        for(int h = 3; h < (brown + 4) / 2; h++) {
            int w = (brown + 4) / 2 - h;
            if(yellow == (w - 2) * (h - 2)) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        return answer;
    }
}
