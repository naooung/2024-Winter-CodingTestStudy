package week6;
import java.util.*;

public class 호텔대실 {
    public int solution(String[][] book_time) {
        // 시간을 분으로 변경하여 시작 시각 기준으로 오름차순 정렬
        int[][] book_minute = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            for (int j = 0; j < 2; j++) {
                String s = book_time[i][j];
                int h = Integer.parseInt(s.substring(0, 2));
                int m = Integer.parseInt(s.substring(3, 5));

                book_minute[i][j] = h * 60 + m;
            }
        }
        Arrays.sort(book_minute, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        ArrayList<Integer> usingRooms = new ArrayList<>(); // 사용 중인 객실의 인덱스 저장
        usingRooms.add(0); // 시작 시각이 가장 빠른 손님
        // 전체 예약을 순회하면서 (사용 중인 방의 퇴실 시간 + 10)보다 크면 인덱스 교체, 모두 작으면 인덱스 추가
        for (int i = 1; i < book_minute.length; i++) {
            int newPerson_startMinute = book_minute[i][0];
            boolean changeRoom = false;
            for (int j = 0; j < usingRooms.size(); j++) {
                int usingPerson_endMinute = book_minute[usingRooms.get(j)][1];

                if (usingPerson_endMinute + 10 <= newPerson_startMinute) {
                    usingRooms.set(j, i);
                    changeRoom = true;
                    break;
                }
            }
            if (changeRoom == false)
                usingRooms.add(i);
        }

        return usingRooms.size();
    }
}