import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final char[] dirChar = {'U', 'D', 'L', 'R'};
    static int n, m;
    static char[][] grid;

    static boolean inBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static boolean isFree(int x, int y) {
        return inBounds(x, y) && grid[x][y] != '#';
    }

    static int[][][] dist;
    static char[][][] move;
    static int[][][] prevX, prevY, prevO;

    static boolean canPlace(int x1, int y1, int x2, int y2) {
        return isFree(x1, y1) && isFree(x2, y2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        grid = new char[n][m];
        int sx = -1, sy = -1, ex = -1, ey = -1;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'C') {
                    sx = i;
                    sy = j;
                }
                if (grid[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        dist = new int[n][m][3];
        move = new char[n][m][3];
        prevX = new int[n][m][3];
        prevY = new int[n][m][3];
        prevO = new int[n][m][3];

        for (int[][] d : dist){
            for (int[] row : d){
                Arrays.fill(row, -1);
            }
        }


        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy, 0});
        dist[sx][sy][0] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], o = curr[2];

            for (int d = 0; d < 4; d++) {
                int nx = x, ny = y, no = -1;

                if (o == 0) { // parado
                    if (d == 0 && canPlace(x - 2, y, x - 1, y)) {
                        nx = x - 2; ny = y; no = 2;
                    } else if (d == 1 && canPlace(x + 1, y, x + 2, y)) {
                        nx = x + 1; ny = y; no = 2;
                    } else if (d == 2 && canPlace(x, y - 2, x, y - 1)) {
                        nx = x; ny = y - 2; no = 1;
                    } else if (d == 3 && canPlace(x, y + 1, x, y + 2)) {
                        nx = x; ny = y + 1; no = 1;
                    }
                } else if (o == 1) { // acostado horizontal
                    if (d == 0 && canPlace(x - 1, y, x - 1, y + 1)) {
                        nx = x - 1; ny = y; no = 1;
                    } else if (d == 1 && canPlace(x + 1, y, x + 1, y + 1)) {
                        nx = x + 1; ny = y; no = 1;
                    } else if (d == 2 && isFree(x, y - 1)) {
                        nx = x; ny = y - 1; no = 0;
                    } else if (d == 3 && isFree(x, y + 2)) {
                        nx = x; ny = y + 2; no = 0;
                    }
                } else if (o == 2) { // acostado vertical
                    if (d == 0 && isFree(x - 1, y)) {
                        nx = x - 1; ny = y; no = 0;
                    } else if (d == 1 && isFree(x + 2, y)) {
                        nx = x + 2; ny = y; no = 0;
                    } else if (d == 2 && canPlace(x, y - 1, x + 1, y - 1)) {
                        nx = x; ny = y - 1; no = 2;
                    } else if (d == 3 && canPlace(x, y + 1, x + 1, y + 1)) {
                        nx = x; ny = y + 1; no = 2;
                    }
                }

                if (no != -1 && dist[nx][ny][no] == -1) {
                    dist[nx][ny][no] = dist[x][y][o] + 1;
                    move[nx][ny][no] = dirChar[d];
                    prevX[nx][ny][no] = x;
                    prevY[nx][ny][no] = y;
                    prevO[nx][ny][no] = o;
                    q.add(new int[]{nx, ny, no});
                }
            }
        }

        if (dist[ex][ey][0] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(dist[ex][ey][0]);
            StringBuilder path = new StringBuilder();
            int x = ex, y = ey, o = 0;

            while (dist[x][y][o] > 0) {
                path.append(move[x][y][o]);
                int tx = prevX[x][y][o];
                int ty = prevY[x][y][o];
                int to = prevO[x][y][o];
                x = tx;
                y = ty;
                o = to;
            }

            System.out.println(path.reverse().toString());
        }
    }
}