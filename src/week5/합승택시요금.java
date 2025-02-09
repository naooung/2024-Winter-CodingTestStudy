package week5;
// 다익스트라랑 플로이드 공부하기...

import java.util.*;
public class 합승택시요금 {
    private int n;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        int answer = Integer.MAX_VALUE;

        int[] startS = calcCost(s, fares);
        int[] startA = calcCost(a, fares);
        int[] startB = calcCost(b, fares);

        for (int i = 1; i <= n; i++)
            answer = Math.min(answer, startS[i] + startA[i] + startB[i]);

        return answer;
    }

    // 다익스트라 알고리즘 사용하여 최소 비용 계산
    public int[] calcCost(int start, int[][] fares) {
        int[] minCost = new int[n + 1];

        for (int i = 1; i <= n; i++) { // [0]은 사용하지 않음
            if (start == i) continue;
            minCost[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Integer> deque = new PriorityQueue<>(); // 방문한 노드 저장
        deque.add(start);

        while (!deque.isEmpty()) {
            int node = deque.poll();
            int currentCost = minCost[node];

            for (int i = 0; i < fares.length; i++) {
                int point1 = fares[i][0];
                int point2 = fares[i][1];
                int cost = fares[i][2];

                // 양방향 확인
                if (point1 == node) {
                    if (minCost[point2] > currentCost + cost) {
                        minCost[point2] = currentCost + cost;
                        deque.add(point2);
                    }
                }

                if (point2 == node) {
                    if (minCost[point1] > currentCost + cost) {
                        minCost[point1] = currentCost + cost;
                        deque.add(point1);
                    }
                }
            }
        }

        return minCost;
    }
}