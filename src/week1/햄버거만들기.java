package week1;

import java.util.ArrayList;

public class 햄버거만들기 {
    public int solution(int[] ingredient) {
        int count = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : ingredient) {
            list.add(i);

            // 최근 4개의 값만 확인하여 반복문 줄이기
            if (list.size() >= 4) {
                if ((list.get(list.size() - 4) == 1) &&
                        (list.get(list.size() - 3) == 2) &&
                        (list.get(list.size() - 2) == 3) &&
                        (list.get(list.size() - 1) == 1)) {
                    for (int j = 0; j < 4; j++)
                        list.remove(list.size() - 1);
                    count++;
                }
            }
        }
        return count;
    }
}

/* 시도 1회차 코드
=> 테스트 케이스 12개 성공, 6개 시간초과 => 반복문 횟수를 줄이거나, 인덱스 삭제가 빠른 ArrayDeque 사용해야겠다고 생각
    public int solution(int[] ingredient) {
        int count = 0;
        int current = 0;
        int result[] = {1,2,3,1};

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : ingredient)
            list.add(i);

        // 빵 포장 == list에서 해당 인덱스 삭제 후 반복문 재실행
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == result[current]) {
                    current++;
                    if (current == 4) {
                        // 포장: list에서 4개 삭제 (뒤에서부터!!)
                        for (int j = 0; j < 4; j++) list.remove(i - j);
                        count++;
                        current = 0;
                        i = -1;
                    }
                } else {
                    current = 0;
                    if (list.get(i) == 1)
                        current = 1;
                }
            }
        return count;
    }
 */