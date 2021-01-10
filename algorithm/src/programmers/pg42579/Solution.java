package programmers.pg42579;

import java.util.*;

class Song implements Comparable<Song> {
    int index;
    int play;

    public Song() {}
    public Song(int index, int play) {
        this.index = index;
        this.play = play;
    }

    @Override
    public int compareTo(Song o) {
        if (this.play != o.play) {
            if (this.play > o.play) { return -1; }
            else { return 1; }
        } else {
            if (this.index < o.index) { return -1; }
            else { return 1; }
        }
    }
}

class Solution {
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        // 장르 별 총 재생 수 세기
        Map<String, Integer> jenreHm = new HashMap<String, Integer>();
        for(int i = 0; i < genres.length; i++) {
            String jenre = genres[i];
            if(jenreHm.get(jenre) == null) {
                jenreHm.put(jenre, plays[i]);
            }else {
                jenreHm.put(jenre, jenreHm.get(jenre) + plays[i]);
            }
        }

        // 총 재생 수의 내림차순으로 정렬
        // List에 옮겨 정렬한 후 LinkedHashMap으로 넘겨 순서 유지
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
        for(Map.Entry<String, Integer> entry : jenreHm.entrySet()) {
            list.add(entry);
        }
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<String, Integer> sortedJenreHm = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry : list) {
            sortedJenreHm.put(entry.getKey(), entry.getValue());
        }

        // Map<String, ArrayList<Song>>으로 멀티 맵 구현
        Map<String, ArrayList<Song>> multiHm = new LinkedHashMap<String, ArrayList<Song>>();
        for(String jenre : sortedJenreHm.keySet()) {
            multiHm.put(jenre, new ArrayList<Song>());
        }
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            multiHm.get(genre).add(new Song(i, plays[i]));
        }

        // 장르 별 최다 재생 곡 2개 뽑아서 저장
        ArrayList<Integer> ansList = new ArrayList<Integer>();
        for(ArrayList<Song> aList : multiHm.values()) {
            Collections.sort(aList);
            ansList.add(aList.get(0).index);
            if(aList.size() >= 2) {
                ansList.add(aList.get(1).index);
            }
        }
        return ansList;
    }
}
