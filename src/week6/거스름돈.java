package week6;

public class 거스름돈 {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < money.length; i++) {
            int coin = money[i];
            for (int j = 0; j <= n; j++) {
                int price = j;
                if (coin <= price) // 동전을 사용할 수 있으면,
                    dp[price] += dp[price - coin]; // 동전을 사용하기 전의 수를 만들 수 있는 경우의 수
            }
        }

        return dp[n] % 1000000007;
    }
}

/*      0   1   2   3   4   5
    1   1   1   1   1   1   1
    2           1   1   2   2
    5                       1
    dp  1   1   2   2   3   4   => 이전 계산값을 사용하여 중복을 해결한 값
*/