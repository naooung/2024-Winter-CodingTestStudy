package week7;
import java.util.*;

public class 과제진행하기 {
    public String[] solution(String[][] plans) {
        ArrayList<String> result = new ArrayList<>(); // 완료된 plans[i][0]을 넣을 예정

        // start를 분으로 변경하기
        int[][] startMinute = new int[plans.length][2];
        int[] endMinute = new int[plans.length];

        for (int i = 0; i < plans.length; i++) {
            String start = plans[i][1];
            int h = Integer.parseInt(start.substring(0, 2));
            int m = Integer.parseInt(start.substring(3, 5));
            startMinute[i][0] = i; // 인덱스 저장
            startMinute[i][1] = h * 60 + m;
            endMinute[i] = Integer.parseInt(plans[i][2]);
        }

        // start가 빠른 과제 순서로 정렬 (앞으로 startMinute[i][0]을 통해 시작시간 빠른 순서대로 인덱스 접근)
        Arrays.sort(startMinute, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        Stack<Integer> stack = new Stack<>();
        stack.push(startMinute[0][0]);
        int currentTime = startMinute[0][1]; // 첫 번째 과제의 시작 시각

        for (int i = 1; i < plans.length; i++) {
            int newWork = startMinute[i][0];
            int newStart = startMinute[i][1];

            // 시작시간 종료시간만 보는게 아니라 잔여시간을 확인해야 하는 문제였음...
            while (!stack.isEmpty()) {
                int recent = stack.peek(); // 스택 맨 위 (진행 중인 과제)
                int remaining = endMinute[recent]; // 해당 과제의 남은 시간
                int availableTime = newStart - currentTime; // 새 과제 시작 전까지 사용 가능한 시간

                if (availableTime <= 0) break; // 시간이 없으면 종료

                // (1) 현재 과제를 전부 끝낼 수 있는 경우
                if (remaining <= availableTime) {
                    currentTime += remaining; // 남은 시간만큼 진행
                    stack.pop(); // 완료된 과제 제거
                    result.add(plans[recent][0]); // 완료된 과제 저장
                }
                // (2) 현재 과제가 끝나지 않는다면, 일부만 진행 후 멈춤
                else {
                    endMinute[recent] -= availableTime; // 남은 시간 업데이트
                    currentTime = newStart; // 현재 시간을 새로운 과제 시작 시간으로 업데이트
                    break;
                }
            }

            // 🔹 새로운 과제 시작 (현재 시간 조정 후 push)
            currentTime = Math.max(currentTime, newStart);
            stack.push(newWork);
        }

        // 새로운 과제가 더이상 없으면 최근에 멈춘 과제들부터 다 처리
        while (!stack.isEmpty())
            result.add(plans[stack.pop()][0]);

        return result.toArray(new String[result.size()]);
    }
}
