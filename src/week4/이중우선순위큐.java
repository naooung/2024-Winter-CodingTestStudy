package week4;
import java.util.*;

public class 이중우선순위큐 {
    private PriorityQueue<Integer> minHeap;

    public int[] solution(String[] operations) {
        minHeap = new PriorityQueue<>();

        for (String s : operations) {
            if (s.charAt(0) == 'I') { // 숫자 삽입
                StringBuilder sd = new StringBuilder();
                for (int i = 2; i < s.length(); i++)
                    sd.append(s.charAt(i));
                minHeap.add(Integer.valueOf(sd.toString()));
            }
            else if (!minHeap.isEmpty()) {
                if (s.equals("D 1")) // 최댓값 삭제
                    pollMax();
                else if (s.equals("D -1")) // 최솟값 삭제
                    minHeap.poll();
            }
        }

        int[] result = {0, 0};
        if (!minHeap.isEmpty()) {
            result[1] = minHeap.peek();
            result[0] = pollMax();
        }
        return result;
    }
    // 큐의 최댓값을 삭제하고 리턴하는 함수
    public int pollMax() {
        PriorityQueue<Integer> temp = new PriorityQueue<>();

        while (minHeap.size() > 1) // 최소 힙에서 마지막 하나만 남을 때까지 삭제 연산 진행
            temp.add(minHeap.poll());
        int max = minHeap.poll();

        while (!temp.isEmpty())
            minHeap.add(temp.poll());
        return max;
    }
}