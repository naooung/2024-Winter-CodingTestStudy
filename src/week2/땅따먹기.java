package week2;

public class 땅따먹기 {
    int solution(int[][] land) {
        int n = land.length;
        int result[][] = new int[n][4];

        // 초기값 설정
        for (int i = 0; i < 4; i++) {
            result[0][i] = land[0][i];
        }

        // 이중 반복문 돌면서 result 배열 갱신
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;

                // 이전 땅 중 가장 점수가 큰 땅을 구하고 현재에 더하기
                for (int k = 0; k < 4; k++) {
                    // 동일한 열 이동 불가 처리
                    if (j != k)
                        if (max < result[i - 1][k])
                            max = result[i - 1][k];
                }
                result[i][j] = land[i][j] + max;
            }
        }

        // 마지막 행의 열 중 최대 점수 구하기
        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (max < result[n - 1][i])
                max = result[n - 1][i];
        }

        return max;
    }
}
