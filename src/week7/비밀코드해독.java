package week7;
import java.util.HashSet;

public class 비밀코드해독 {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;

        for (int a = 1; a <= n; a++) {
            for (int b = a + 1; b <= n; b++) {
                for (int c = b + 1; c <= n; c++) {
                    for (int d = c + 1; d <= n; d++) {
                        for (int e = d + 1; e <= n; e++) {
                            HashSet<Integer> set = new HashSet<>();
                            set.add(a);
                            set.add(b);
                            set.add(c);
                            set.add(d);
                            set.add(e);

                            boolean allRight = true;
                            for (int i = 0; i < q.length; i++) {
                                int same = 0; // 같은 행에 동일한 수
                                for (int j = 0; j < q[0].length; j++)
                                    if (set.contains(q[i][j]))
                                        same++;

                                if (same != ans[i]) {
                                    allRight = false;
                                    break;
                                }
                            }
                            if (allRight)
                                answer++;
                        }
                    }
                }
            }
        }
        return answer;
    }
}
