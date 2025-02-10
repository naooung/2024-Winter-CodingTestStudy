package week6;
import java.util.*;

public class re합승택시요금 {
    private int n;
    private ArrayList<Node>[] list;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        list = new ArrayList[n + 1]; // 배열 초기화

        for (int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];

            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }

        int[] startS = dijkstra(s);
        int[] startA = dijkstra(a);
        int[] startB = dijkstra(b);

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) // 경유지 i 중 어디를 거쳐가는게 요금이 적은지 확인
            answer = Math.min(answer, startS[i] + startA[i] + startB[i]);

        return answer;
    }

    // 다익스트라 알고리즘 사용하여 최소 비용 계산
    public int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); // 배열 초기화

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (distance[now.to] < now.cost) continue; // 이미 최적 경로가 발견된 경우 건너뜀

            for (Node next : list[now.to]) {
                if (distance[next.to] > now.cost + next.cost) {
                    distance[next.to] = now.cost + next.cost;
                    pq.add(new Node(next.to, distance[next.to]));
                }
            }
        }

        return distance;
    }
}

class Node {
    int to;
    int cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
