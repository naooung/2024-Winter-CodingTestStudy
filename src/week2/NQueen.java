package week2;

public class NQueen {

}

/* 실패 코드 1
class Solution {
    private static int n;
    private static int chess[][];

    public int solution(int n) {
        this.n = n;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            chess = new int[n][n];
            if (makeChess(i))
                answer++;
        }
        return answer;
    }

    public boolean makeChess(int q1) {
        Queen q = new Queen(0, q1);
        int qNum = 1;
        chess[q.x][q.y] = 2;

        while (qNum < n) { // n개의 q 위치를 정할 때까지 실행
            boolean select = false;
            checkMove(q);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (chess[i][j] == 0) {
                        select = true;
                        q = new Queen(i, j);
                        chess[i][j] = 2;
                        qNum++;
                        break;
                    }
                }
                if (select)
                    break;
            }
            if (!select)
                return false;
        }
        return true;
    }

    // Q의 가로 세로 대각선을 처리하는 함수
    public void checkMove(Queen q) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (chess[i][j] != 0)
                    continue;
                else if (i == q.x || j == q.y || (Math.abs(i - q.x) == Math.abs(j - q.y)))
                    chess[i][j] = 1;
            }
        }
    }
}

class Queen {
    int x;
    int y;

    public Queen(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 */

/* 실패 코드 2
class Solution {
    private static int n;
    private static int[][] chess;
    private static int[][] beforeChess; // 이전 상태를 저장하는 배열

    public int solution(int n) {
        this.n = n;
        int answer = 0;

        for (int i = 0; i < n; i++) { // 첫 번째 퀸의 모든 열 시도
            chess = new int[n][n];
            beforeChess = new int[n][n]; // 이전 상태 저장 배열 초기화
            if (makeChess(i))
                answer++;
        }
        return answer;
    }

    public boolean makeChess(int q1) {
        Stack<Queen> stack = new Stack<>();
        stack.push(new Queen(0, q1));
        chess[0][q1] = 2; // 첫 번째 퀸 배치

        while (!stack.isEmpty()) {
            Queen current = stack.peek(); // 스택의 마지막 퀸
            boolean placed = false;

            // 현재 체스판 상태 저장
            saveAndRestoreChess(true); // 상태 저장

            // 현재 퀸 위치에 따른 공격 위치 표시
            checkMove(current);

            for (int i = current.x + 1; i < n; i++) { // 다음 행부터 탐색
                for (int j = 0; j < n; j++) {
                    if (chess[i][j] == 0) { // 퀸을 배치할 수 있는 위치
                        stack.push(new Queen(i, j));
                        chess[i][j] = 2; // 퀸 배치
                        placed = true;
                        break;
                    }
                }
                if (placed) break;
            }

            if (!placed) { // 배치할 위치가 없으면 백트래킹
                stack.pop();
                saveAndRestoreChess(false); // 상태 복구
            }

            if (stack.size() == n) return true; // 모든 퀸 배치 성공
        }
        return false;
    }

    // Q의 가로, 세로, 대각선의 공격 위치를 업데이트
    private void checkMove(Queen q) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chess[i][j] == 0 &&
                        (i == q.x || j == q.y || Math.abs(i - q.x) == Math.abs(j - q.y))) {
                    chess[i][j] = 1; // 공격 위치 표시
                }
            }
        }
    }

    // 상태를 저장하거나 복구하는 함수
    private void saveAndRestoreChess(boolean save) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (save) {
                    beforeChess[i][j] = chess[i][j]; // 상태 저장
                } else {
                    chess[i][j] = beforeChess[i][j]; // 상태 복구
                }
            }
        }
    }
}

class Queen {
    int x, y;

    public Queen(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

 */