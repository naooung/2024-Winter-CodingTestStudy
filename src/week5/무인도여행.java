package week5;
import java.util.*;

public class 무인도여행 {
    private ArrayList<Integer> islands = new ArrayList<>();
    private int[][] visited;

    public int[] solution(String[] maps) {
        visited = new int[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                char c = maps[i].charAt(j);

                // 좌표가 숫자이고 아직 방문하지 않았다면 bfs로 전체 크기를 계산하여 섬에 추가
                if (c != 'X' && visited[i][j] == 0)
                    islands.add(bfs(maps, i, j));
            }
        }

        if (islands.size() == 0)
            return new int[]{-1};
        else {
            Collections.sort(islands);

            int[] result = new int[islands.size()];
            for (int i = 0; i < result.length; i++)
                result[i] = islands.get(i);

            return result;
        }
    }

    public int bfs(String[] maps, int row, int col) {
        int offset[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{row, col});
        int islandSize = maps[row].charAt(col) - '0';
        visited[row][col] = 1;

        while (!deque.isEmpty()) {
            int[] d = deque.poll();

            // 상하좌우 체크하면서 방문하지 않았다면 방문 처리 후 island 크기 증가
            for (int i = 0; i < 4; i++) {
                int newRow = d[0] + offset[i][0];
                int newCol = d[1] + offset[i][1];

                if (newRow >= 0 && newCol >= 0 && newRow < maps.length && newCol < maps[0].length() // 지도 범위 안인지 확인
                        && visited[newRow][newCol] == 0 && maps[newRow].charAt(newCol) != 'X') {

                    visited[newRow][newCol] = 1;
                    islandSize += maps[newRow].charAt(newCol) - '0';
                    deque.add(new int[]{newRow, newCol});
                }
            }
        }

        return islandSize;
    }
}