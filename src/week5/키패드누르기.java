package week5;

import java.util.*;

public class 키패드누르기 {
    private int key[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};
    private int currentLeft = -1;
    private int currentRight = -2;
    private StringBuilder sd = new StringBuilder();

    public String solution(int[] numbers, String hand) {

        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];

            if (n == 1 || n == 4 || n == 7) // 왼손 사용
                useHand("left", n);
            else if (n == 3 || n == 6 || n == 9) // 오른손 사용
                useHand("right", n);
            else { // 거리 확인 후 사용
                int leftDistance = calcDistance(currentLeft, n);
                int rightDistance = calcDistance(currentRight, n);

                if (leftDistance < rightDistance) // 왼손 사용
                    useHand("left", n);
                else if (leftDistance > rightDistance) // 오른손 사용
                    useHand("right", n);
                else // 거리가 같으면 어느손잡인지 인자로 전달
                    useHand(hand, n);
            }
        }

        return sd.toString();
    }

    public void useHand(String direction, int number) {
        if (direction.equals("left")) {
            currentLeft = number;
            sd.append("L");
        }
        else {
            currentRight = number;
            sd.append("R");
        }
    }

    // 현재 손 위치와 목표 위치의 좌표를 구한 후 거리를 계산하는 함수
    public int calcDistance(int handNumber, int goalNumber) {
        int handX = 0, handY = 0, goalX = 0, goalY = 0;

        for (int i = 0; i < key.length; i++)
            for (int j = 0; j < key[0].length; j++) {
                if (key[i][j] == handNumber) {
                    handX = i;
                    handY = j;
                }
                if (key[i][j] == goalNumber) {
                    goalX = i;
                    goalY = j;
                }
            }

        return Math.abs(handX - goalX) + Math.abs(handY - goalY);
    }
}