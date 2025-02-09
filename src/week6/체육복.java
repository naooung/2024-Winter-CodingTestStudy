package week6;
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        HashSet<Integer> reserveSet = new HashSet<>(); // 여벌옷이 있는 학생 집합
        for (int i : reserve)
            reserveSet.add(i);

        HashSet<Integer> lostSet = new HashSet<>(); // 도난당한 학생 집합
        for (int i : lost) {
            if (reserveSet.contains(i)) // ! 예외: 여벌옷이 있는 학생이 도난당했을 경우는 제외
                reserveSet.remove(i);
            else
                lostSet.add(i);
        }

        for (int i : reserveSet) {
            if (lostSet.contains(i - 1)) // 앞번호 학생
                lostSet.remove(i - 1);
            else if (lostSet.contains(i + 1)) // 뒷번호 학생
                lostSet.remove(i + 1);
        }

        return n - lostSet.size();
    }
}
