package week3;

import java.util.*;

public class 거리두기확인하기 {
    public int[] solution(String[][] places) {
        int[] result = new int[5];

        for (int i = 0; i < 5; i++) {
            ArrayList<Point> person = new ArrayList<>();

            // 1. 대기실 i의 응시자가 앉아있는 자리의 Point(x,y) 저장
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++)
                    if (places[i][j].charAt(k) == 'P')
                        person.add(new Point(j, k));
            }

            // 2. person 리스트 간의 거리두기 확인
            boolean ok = true;
            for (int j = 0; j < person.size(); j++) {
                for (int k = j + 1; k < person.size(); k++) {                                                           
                    Point p1 = person.get(j);
                    Point p2 = person.get(k);

                    // 맨해튼 거리가 2 이하이고, 파티션이 없으면 false하고 반복문 종료 (거리두기 실패)
                    if ((Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y)) <= 2 && !partition(places[i], p1, p2)) {
                        ok = false;
                        break;
                    }
                }
                if (ok == false) break;
            }
            result[i] = ok ? 1 : 0;
        }
        return result;
    }

    public boolean partition(String[] place, Point p1, Point p2) {
        // 1. 같은 행에 있을 때 중간 지점 확인
        if (p1.x == p2.x) {
            int midY = (p1.y + p2.y) / 2;
            return place[p1.x].charAt(midY) == 'X';
        }

        // 2. 같은 열에 있을 때 중간 지점 확인
        if (p1.y == p2.y) {
            int midX = (p1.x + p2.x) / 2;
            return place[midX].charAt(p1.y) == 'X';
        }

        // 3. 대각선에 있을 때 두 경로 확인
        return place[p1.x].charAt(p2.y) == 'X' && place[p2.x].charAt(p1.y) == 'X';
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
