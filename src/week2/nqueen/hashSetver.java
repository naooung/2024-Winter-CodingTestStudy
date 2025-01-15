package week2.nqueen;
import java.util.*;

class hashSetver {
    int n;
    int count;
    HashSet<Integer> cols;      // 열 정보를 저장
    HashSet<Integer> diag1;     // ↘ 방향 대각선 (row - col)
    HashSet<Integer> diag2;     // ↙ 방향 대각선 (row + col)

    public int solution(int n) {
        // 전역 변수 초기화
        this.n = n;
        count = 0;
        cols = new HashSet<>();
        diag1 = new HashSet<>();
        diag2 = new HashSet<>();

        // 백트래킹 시작
        nQueen(0);

        return count;
    }

    public void nQueen(int row) {
        // 모든 행에 퀸을 배치한 경우
        if (row == n) {
            count++;
            return;
        }

        // 현재 행의 모든 열에 대해 시도
        for (int col = 0; col < n; col++) {
            // 현재 열, ↘ 대각선, ↙ 대각선 사용 여부 확인
            if (cols.contains(col) || diag1.contains(row - col) || diag2.contains(row + col)) {
                continue;
            }

            // 현재 위치에 퀸 배치
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            // 다음 행으로 진행
            nQueen(row + 1);

            // 백트래킹: 퀸 제거
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }
}