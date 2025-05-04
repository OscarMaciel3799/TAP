import java.util.*;

public class Main {
    static char[][] plantillaT = {
            {'#','#','#'},
            {'*','#','*'},
            {'*','#','*'},
            {'*','#','*'},
            {'*','#','*'}
    };

    static char[][] plantillaA = {
            {'#','#','#'},
            {'#','.','#'},
            {'#','#','#'},
            {'#','.','#'},
            {'#','.','#'}
    };

    static char[][] plantillaP = {
            {'#','#','#'},
            {'#','.','#'},
            {'#','#','#'},
            {'#','*','*'},
            {'#','*','*'}
    };

    static int filas = 5, cols = 3;

    static char[][] grilla;
    static boolean[][] visitado;
    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        grilla = new char[N][M];
        visitado = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            grilla[i] = sc.nextLine().toCharArray();
        }

        int countT = 0, countA = 0, countP = 0;

        for (int i = 0; i <=N - filas; i++) {
            for (int j = 0; j <=M - cols; j++) {
                if (puedeSerLetra(i, j, plantillaT, filas, cols, false)) {
                    marcarVisitados(i, j, plantillaT);
                    countT++;
                }else if (puedeSerLetra(i, j, plantillaA, filas, cols, true)) {
                    marcarVisitados(i, j, plantillaA);
                    countA++;
                } else if (puedeSerLetra(i, j, plantillaP, filas, cols, true)) {
                    marcarVisitados(i, j, plantillaP);
                    countP++;
                }
            }
        }
        System.out.println(countT + " " + countA + " " + countP);

    }

    static boolean puedeSerLetra(int x, int y, char[][] plantilla, int filas, int cols, boolean exacto) {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {

                if (x + i > N || y + j > M) return false;
                if (visitado[x + i][y + j]) return false;

                char esperado = plantilla[i][j];
                char real = grilla[x + i][y + j];
                if (esperado == '#') {
                    if (real != '#') return false;
                } else if (exacto && esperado == '.') {
                    if (real != '.') return false;
                }
            }
        }
        return true;
    }

    static void marcarVisitados(int x, int y, char[][] plantilla) {
        int filas = plantilla.length;
        int cols = plantilla[0].length;

        for (int i = 0; i <filas; i++) {
            for (int j = 0; j < cols; j++) {
                if (x + i < N && y + j < M) {
                    if (plantilla[i][j] == '#' || plantilla[i][j] == '.') {
                        visitado[x + i][y + j] = true;
                    }
                }
            }
        }
    }

}

