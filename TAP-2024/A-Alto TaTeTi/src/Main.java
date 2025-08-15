import java.util.*;

public class Main {
    static boolean[][] block = new boolean[10][10]; // block[a][b] = B bloqueado si A vacío
    static int[] winMasks = {
            0b111000000, 0b000111000, 0b000000111, // Filas
            0b100100100, 0b010010010, 0b001001001, // Columnas
            0b100010001, 0b001010100               // Diagonales
    };
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // Leer restricciones iniciales
        for (int i = 0; i < N; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            block[A][B] = true;
        }

        // Cierre transitivo (propagar cadenas de restricciones)
        for (int k = 1; k <= 9; k++) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    if (block[i][k] && block[k][j]) {
                        block[i][j] = true;
                    }
                }
            }
        }

        int result = minimax(0, 0, true);
        if (result == 1) System.out.println("X");
        else if (result == -1) System.out.println("O");
        else System.out.println("E");
    }

    static boolean isWin(int mask) {
        for (int w : winMasks) {
            if ((mask & w) == w) return true;
        }
        return false;
    }

    static List<Integer> getValidMoves(int maskX, int maskO) {
        boolean[] occupied = new boolean[10];
        for (int i = 1; i <= 9; i++) {
            int bit = 1 << (9 - i);
            if ((maskX & bit) != 0 || (maskO & bit) != 0) {
                occupied[i] = true;
            }
        }

        List<Integer> moves = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (occupied[i]) continue;

            boolean blockedNow = false;
            for (int a = 1; a <= 9; a++) {
                if (!occupied[a] && block[a][i]) {
                    blockedNow = true;
                    break;
                }
            }

            if (!blockedNow) moves.add(i);
        }
        return moves;
    }

    static int minimax(int maskX, int maskO, boolean turnX) {
        if (isWin(maskX)) return 1;
        if (isWin(maskO)) return -1;

        String key = maskX + "," + maskO + "," + turnX;
        if (memo.containsKey(key)) return memo.get(key);

        List<Integer> moves = getValidMoves(maskX, maskO);
        if (moves.isEmpty()) return 0;

        int best = turnX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int move : moves) {
            int bit = 1 << (9 - move);
            if (turnX) {
                best = Math.max(best, minimax(maskX | bit, maskO, false));
                if (best == 1) break; // poda
            } else {
                best = Math.min(best, minimax(maskX, maskO | bit, true));
                if (best == -1) break; // poda
            }
        }

        memo.put(key, best);
        return best;
    }
}
