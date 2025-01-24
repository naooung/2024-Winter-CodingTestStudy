package week4;
import java.util.*;

public class 데이터분석 {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int row = data.length;
        int col = data[0].length;

        // 1. ext 값(first) 중 val_ext보다 작은 데이터의 인덱스를 저장
        int first = getIndex(ext);
        ArrayList<Integer> filteredIndices = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            if (data[i][first] < val_ext) {
                filteredIndices.add(i);
            }
        }

        // 3. sort_by(second)를 기준으로 오름차순 정렬
        int second = getIndex(sort_by);
        int[][] result = new int[filteredIndices.size()][col];
        for (int i = 0; i < filteredIndices.size(); i++)
            result[i] = data[filteredIndices.get(i)];

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j][second] > result[j + 1][second]) {
                    int[] temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    int getIndex(String key) {
        if (key.equals("code"))
            return 0;
        else if (key.equals("date"))
            return 1;
        else if (key.equals("maximum"))
            return 2;
        else
            return 3;
    }
}