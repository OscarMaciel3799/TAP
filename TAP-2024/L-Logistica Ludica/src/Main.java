import java.io.*;
import java.util.*;

public class Main {

    // --- FastScanner para I/O rápido (N, Q hasta 2e5) ---
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sgn = 1, x = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * sgn;
        }
    }

    // Bit-trick para potencia de 2 (válido para x > 0)
    static boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int Q = fs.nextInt();

        // Usamos long por seguridad en sumas
        long[] prefPow2Ex1 = new long[N + 1]; // suma de potencias de 2, excluyendo el 1
        long[] prefOddG3    = new long[N + 1]; // suma de impares >= 3
        long[] prefOnes     = new long[N + 1]; // cantidad de '1'

        for (int i = 1; i <= N; i++) {
            int v = fs.nextInt();

            // arrastramos acumulados
            prefPow2Ex1[i] = prefPow2Ex1[i - 1];
            prefOddG3[i]   = prefOddG3[i - 1];
            prefOnes[i]    = prefOnes[i - 1];

            if (v == 1) {
                // común (disputado): solo contamos la CANTIDAD de unos
                prefOnes[i] += 1;
            } else if ((v & 1) == 1) {
                // impar >= 3: exclusivo de Brian
                prefOddG3[i] += v;
            } else {
                // par: ¿es potencia de 2? (excluimos 1 arriba)
                if (isPowerOfTwo(v)) prefPow2Ex1[i] += v; // exclusivo de Agustín
                // pares no potencia de 2 se ignoran
            }
        }

        StringBuilder out = new StringBuilder();

        for (int qi = 0; qi < Q; qi++) {
            int L = fs.nextInt();
            int R = fs.nextInt();

            long sumPow2Ex1 = prefPow2Ex1[R] - prefPow2Ex1[L - 1]; // técnica: prefix sums
            long sumOddG3   = prefOddG3[R]   - prefOddG3[L - 1];   // técnica: prefix sums
            long c1         = prefOnes[R]    - prefOnes[L - 1];    // técnica: prefix sums

            // Reparto óptimo de los '1' (subjuego compartido):
            long onesA = (c1 + 1) / 2; // ceil(c1/2) para Agustín (empieza)
            long onesB = c1 / 2;       // floor(c1/2) para Brian

            long scoreA = sumPow2Ex1 + onesA;
            long scoreB = sumOddG3   + onesB;

            if (scoreA > scoreB) out.append("A\n");
            else if (scoreB > scoreA) out.append("B\n");
            else out.append("E\n");
        }

        System.out.print(out.toString());
    }
}
