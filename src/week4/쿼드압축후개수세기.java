package week4;
public class 쿼드압축후개수세기 {
    private int zero = 0;
    private int one = 0;

    public int[] solution(int[][] arr) {
        makeS(arr, 0, 0, arr.length);

        int[] result = {zero, one};
        return result;
    }

    public void makeS(int[][] zone, int x, int y, int length) {
        // 해당 영역이 모두 같은 수인 경우: 숫자 처리
        if (allSame(zone, x, y, length)) {
            if (zone[x][y] == 0) zero++;
            else one++;
            return;
        }

        // 해당 영역이 다른 수인 경우: 네가지 영역에 대하여 재귀 호출
        int half = length / 2;
        makeS(zone, x, y, half);
        makeS(zone, x, y + half, half);
        makeS(zone, x + half, y, half);
        makeS(zone, x + half, y + half, half);
    }

    // 인자로 받은 배열이 모두 같은 수인지 확인하는 함수
    private boolean allSame(int[][] zone, int x, int y, int length) {
        int same = zone[x][y];

        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (same != zone[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}