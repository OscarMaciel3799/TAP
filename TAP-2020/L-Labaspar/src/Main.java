import java.util.*;

public class Main {
    static int L, C;
    static char[][] grid;
    static Set<String> wordSet;
    static Set<String>[][] cellToWords;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        L = scanner.nextInt();
        C = scanner.nextInt();
        grid = new char[L][C];
        scanner.nextLine();

        for (int i = 0; i < L; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }

        int N = Integer.parseInt(scanner.nextLine());
        wordSet = new HashSet<>();
        List<String> originalWords = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = scanner.nextLine();
            originalWords.add(word);
            wordSet.add(sortString(word));
        }

        cellToWords = new HashSet[L][C];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                cellToWords[i][j] = new HashSet<>();
            }
        }

        // Buscamos los anagramas en la matriz
        for (String word : originalWords) {
            int len = word.length();
            String sortedWord = sortString(word);

            // Evaluamos filas
            for (int i = 0; i < L; i++) {
                for (int j = 0; j <= C - len; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < len; k++) {
                        sb.append(grid[i][j + k]);
                    }
                    if (sortString(sb.toString()).equals(sortedWord)) {
                        for (int k = 0; k < len; k++) {
                            cellToWords[i][j + k].add(word);
                        }
                    }
                }
            }

            // Evaluamos columnas
            for (int i = 0; i <= L - len; i++) {
                for (int j = 0; j < C; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < len; k++) {
                        sb.append(grid[i + k][j]);
                    }
                    if (sortString(sb.toString()).equals(sortedWord)) {
                        for (int k = 0; k < len; k++) {
                            cellToWords[i + k][j].add(word);
                        }
                    }
                }
            }

            // Evaluamos diagonal principal (↘)
            for (int i = 0; i <= L - len; i++) {
                for (int j = 0; j <= C - len; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < len; k++) {
                        sb.append(grid[i + k][j + k]);
                    }
                    if (sortString(sb.toString()).equals(sortedWord)) {
                        for (int k = 0; k < len; k++) {
                            cellToWords[i + k][j + k].add(word);
                        }
                    }
                }
            }

            // Evaluamos diagonal secundaria (↙)
            for (int i = 0; i <= L - len; i++) {
                for (int j = len - 1; j < C; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < len; k++) {
                        sb.append(grid[i + k][j - k]);
                    }
                    if (sortString(sb.toString()).equals(sortedWord)) {
                        for (int k = 0; k < len; k++) {
                            cellToWords[i + k][j - k].add(word);
                        }
                    }
                }
            }
        }

        // Contamos celdas especiales (usadas por al menos 2 palabras distintas)
        int specialCells = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                if (cellToWords[i][j].size() >= 2) {
                    specialCells++;
                }
            }
        }

        System.out.println(specialCells);
    }

    static String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}