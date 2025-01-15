package week3;

import java.util.*;

public class 실패율 {
    public int[] solution(int N, int[] stages) {
        // HashMap<실패율 Double, 스테이지 ArrayList> 생성
        HashMap<Double, ArrayList<Integer>> failure = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int failedUser = 0, reachedUser = 0;

            for (int j = 0; j < stages.length; j++) {
                // 스테이지 i에 머물러 있는 User 수
                if (i == stages[j])
                    failedUser++;

                // 스테이지 i에 도달한 User 수 (stages[User]의 값이 i보다 커야함)
                if (i <= stages[j])
                    reachedUser++;
            }

            // 실패율이 없으면 새로운 ArrayList 생성해서 i 삽입, 있으면 원래 arrayList에서 i 삽입
            if (reachedUser == 0 || failedUser == 0) {
                ArrayList<Integer> list = failure.getOrDefault(0.0, new ArrayList<>());
                list.add(i);
                failure.put(0.0, list);
            } else {
                double rate = (double) failedUser / reachedUser;
                ArrayList<Integer> list = failure.getOrDefault(rate, new ArrayList<>());
                list.add(i);
                failure.put(rate, list);
            }
        }

        // 1. key만 가지고 와서 내림차순 정렬
        List<Double> keys = new ArrayList<>(failure.keySet());
        Collections.sort(keys, Collections.reverseOrder());

        // 2. map에서 해당 key의 value 가져와서 List에 추가
        List<Integer> resultList = new ArrayList<>();
        for (Double key : keys)
            resultList.addAll(failure.get(key));

        // 3. List를 int[]로 변환
        int[] result = new int[N];
        for (int i = 0; i < N; i++)
            result[i] = resultList.get(i);

        return result;
    }
}

/* 예전 코드
class Solution {
    public int[] solution(int N, int[] stages) {
        // HashMap (Key: 스테이지 & Value: 실패율)
		HashMap<Integer, Double> failureRate = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			int failedUser = 0, reachedUser = 0;

			for (int j = 0; j < stages.length; j++) {
				// 스테이지 i에 머물러 있는 User 수
				if (i == stages[j])
					failedUser++;

				// 스테이지 i에 도달한 User 수 (stages[User]의 값이 i보다 커야함)
				if (i <= stages[j])
					reachedUser++;
			}
			 *  reachedUser가 0인 경우(0/0) 결과: NaN (Not a Number)
			 *  reachedUser이 0인 경우 실패율 0 설정
			failureRate.put(i, reachedUser == 0 ? 0 : (double)failedUser / reachedUser);
        }

        // Value 값이 높은 순서부터 내림차순 정렬하여 배열 반환
        return failureRate.entrySet().stream()
									 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // HashMap.Entry 객체에서 Key 값을 가져와서 int로 mapping
                                     .mapToInt(HashMap.Entry::getKey)
									 .toArray();
    }
}
 */