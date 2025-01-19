package week3;
import java.util.*;

// bfs로 풀이한 ver.
public class 숫자변환하기 {
    public int solution(int x, int y, int n) {
        int count = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(y);

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int newY = q.poll();
                if (newY == x)
                    return count;

                if (newY % 3 == 0)
                    q.add(newY / 3);

                if (newY % 2 == 0)
                    q.add(newY / 2);

                if (newY - n >= x)
                    q.add(newY - n);
            }
            count++;
        }
        return -1;
    }
}

// dp로 풀이한 ver.
class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];

        for (int i = y; i >= x; i--) {
            if (i != y && dp[i] == 0) {
                dp[i] = -1;
                continue;
            }

            if (i % 2 == 0)
                dp[i / 2] = (dp[i / 2] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i / 2]);

            if (i % 3 == 0)
                dp[i / 3] = (dp[i / 3] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i / 3]);

            if (i - n >= x)
                dp[i - n] = (dp[i - n] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i - n]);

        }
        return dp[x];
    }
}