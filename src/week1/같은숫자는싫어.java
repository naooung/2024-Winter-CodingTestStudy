package week1;

import java.util.ArrayDeque;

public class 같은숫자는싫어 {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 이전 숫자와 다르면 큐에 push
        q.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] != arr[i]) {
                q.addLast(arr[i]);
            }
        }

        // 큐에서 배열로 변환
        int size = q.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = q.pollFirst();
        }

        return result;
    }
}
