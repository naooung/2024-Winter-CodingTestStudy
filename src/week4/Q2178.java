package week4;

import java.util.*;

public class Q2178 {
    private static int n, m;
    private static int count;
    private static int[][] maze;
    private static int[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.nextLine();

        maze = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < m; j++)
                maze[i][j] = s.charAt(j) - '0';
        }

        bfs(0, 0);

        System.out.println(maze[n - 1][m - 1]);
    }

    public static void bfs(int x, int y) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Point now = q.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || maze[nextX][nextY] == 0)
                    continue;
                else if (visited[nextX][nextY] == 1)
                    continue;
                else {
                    q.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = 1;
                    maze[nextX][nextY] = maze[now.x][now.y] + 1;
                }
            }
        }
    }
}
class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
