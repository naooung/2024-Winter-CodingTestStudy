package week2.nqueen;

import java.util.Scanner;
public class Q30242 {
    static int n;
    static int[] placedQueens;
    static int[] answer;
    static boolean[] usedCol;
    static boolean[] usedDiag1;
    static boolean[] usedDiag2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        placedQueens = new int[n];
        answer = new int[n];

        for (int i = 0; i < n; i++)
            placedQueens[i] = scanner.nextInt();

        usedCol   = new boolean[n];
        usedDiag1 = new boolean[2 * n - 1];
        usedDiag2 = new boolean[2 * n - 1];

        for (int row = 0; row < n; row++) {
            if (placedQueens[row] != 0) {
                int col = placedQueens[row] - 1;

                // 다 불가능한 위치일 때
                if (usedCol[col] || usedDiag1[row - col + (n - 1)] || usedDiag2[row + col]) {
                    System.out.println(-1);
                    return;
                }
                else {
                    usedCol[col] = true;
                    usedDiag1[row - col + (n - 1)] = true;
                    usedDiag2[row + col] = true;
                    answer[row] = col;
                }
            }
        }

        if (!dfs(0)) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print((answer[i] + 1) + " ");
            }
        }
    }

    public static boolean dfs(int row) {
        if (row == n) {
            return true;
        }

        // 이미 퀸이 있는 행이면 pass
        if (placedQueens[row] != 0) {
            return dfs(row + 1);
        }

        for (int col = 0; col < n; col++) {
            if (!usedCol[col]
                    && !usedDiag1[row - col + (n - 1)]
                    && !usedDiag2[row + col]) {

                // 배치
                usedCol[col] = true;
                usedDiag1[row - col + (n - 1)] = true;
                usedDiag2[row + col] = true;
                answer[row] = col;

                if (dfs(row + 1))
                    return true;

                // 아니면 해제
                usedCol[col] = false;
                usedDiag1[row - col + (n - 1)] = false;
                usedDiag2[row + col] = false;
            }
        }
        // 모든 열에 둘 자리가 없음!!
        return false;
    }
}

/* 시간 초과 코드
public class Q30242 {
    private static int[] chess;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] placedQueens = new int[n];
        chess = new int[n];

        // 이미 놓인 퀸들의 위치 입력
        for (int i = 0; i < n; i++) {
            placedQueens[i] = scanner.nextInt();
        }

        // 이미 놓인 퀸 배치
        for (int i = 0; i < n; i++) {
            if (placedQueens[i] != 0) {
                chess[i] = placedQueens[i] - 1;
            }
        }

        // 백트래킹을 이용하여 나머지 퀸 배치
        if (!nQueen(0, placedQueens)) {
            System.out.println(-1);  // 배치 불가능한 경우
        } else {
            // 결과 출력
            for (int i = 0; i < n; i++) {
                System.out.print((chess[i] + 1) + " ");
            }
        }
    }

    // 백트래킹을 사용하여 퀸 배치
    public static boolean nQueen(int index, int[] placedQueens) {
        if (index == n) {  // 모든 퀸을 배치한 경우
            return true;
        }

        // 이미 퀸이 놓여있는 행은 pass
        if (placedQueens[index] != 0) {
            // 이미 놓여있는 위치가 유효하지 않다면 바로 실패
            if (!canMove(index)) {
                return false;
            }
            return nQueen(index + 1, placedQueens);
        }

        // 현재 행에 퀸을 놓을 수 있는 열을 모두 시도
        for (int col = 0; col < n; col++) {
            chess[index] = col;

            if (canMove(index)) {
                if (nQueen(index + 1, placedQueens)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 열 대각선 확인
    public static boolean canMove(int index) {
        for (int i = 0; i < index; i++) {
            if (chess[i] == chess[index] || Math.abs(i - index) == Math.abs(chess[index] - chess[i])) {
                return false;
            }
        }
        return true;
    }
}
*/
