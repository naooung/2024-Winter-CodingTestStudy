package week7;

public class 지게차와크레인 {
    char[][] container;
    int n;
    int m;

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        // 1. 컨테이너 배열 생성
        container = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = storage[i].charAt(j);
                container[i][j] = c;
            }
        }

        // 2. request 명령 처리
        for (String q : requests) {
            if (q.length() == 2)
                allClear(q.charAt(0)); // 크레인
            else
                outClear(q.charAt(0)); // 지게차
        }

        // 3. 빈 문자가 아닌 곳 개수 return
        int answer = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (container[i][j] != '\0')
                    answer++;

        return answer;
    }
    // 크레인 사용: 해당되는 모든 알파벳 꺼내기
    public void allClear(char target) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (container[i][j] == target)
                    container[i][j] = '\0';
    }

    // 지게차 사용: 해당되는 알파벳 중 외부에 있는 것만 꺼내기
    public void outClear(char target) {
        char[][] temp = new char[n][m]; // 중복 꺼내기를 막기 위한 원본 배열 저장
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                temp[i][j] = container[i][j];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) { // 테두리에서만 dfs 실행
                    if (temp[i][j] == target)
                        container[i][j] = '\0';
                    else if (temp[i][j] == '\0') // 문자가 없다면 dfs 실행
                        dfs(temp, visited, i, j, target);
                }
            }
        }
    }

    public void dfs(char[][] temp, int[][] visited, int row, int col, char target) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[row][col] = 1;

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // 범위를 벗어나면 continue
            if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || visited[newRow][newCol] == 1)
                continue;

            if (temp[newRow][newCol] == target)
                container[newRow][newCol] = '\0';
            else if (temp[newRow][newCol] == '\0') // 문자가 없다면 dfs 실행
                dfs(temp, visited, newRow, newCol, target);
        }
    }
}
