package week1;

import java.util.Stack;

public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        // n자리의 이진수로 변환하여 배열에 저장
        int[][] a1 = new int[n][n];
        int[][] a2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            Stack<Integer> a1Stack = new Stack<>();
            Stack<Integer> a2Stack = new Stack<>();
            int x = arr1[i];
            int y = arr2[i];

            for (int j = 0; j < n - 1; j++) {
                a1Stack.push(x % 2);
                a2Stack.push(y % 2);
                x /= 2; y /= 2;
            }
            a1Stack.push(x); a2Stack.push(y);
            for (int j = 0; j < n; j++) {
                a1[i][j] = a1Stack.pop();
                a2[i][j] = a2Stack.pop();
            }
        }

        // 두 배열의 값을 확인하여 벽 처리
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sd = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((a1[i][j] == 1) || (a2[i][j] == 1))
                    sd.append("#");
                else
                    sd.append(" ");
            }
            result[i] = sd.toString();
        }
        return result;
    }
}
