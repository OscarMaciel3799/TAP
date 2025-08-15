import java.io.*;
import java.util.*;

/**
 * B. Period Search — Conjunto mínimo de preguntas
 * Técnica: factorizar N para obtener sus primos distintos (ω(N)).
 * Consultas: para cada primo p | N, preguntar (L=1, R=N/p).
 * Justificación: cualquier período mínimo d ≤ N/2 divide a N/p para algún p primo que divide N/d.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();

        // --- 1) Factorización de N para obtener PRIMOS DISTINTOS (técnica central) ---
        // Usamos O(√N), guardando cada primo una sola vez.
        int temp = N;
        List<Integer> primes = new ArrayList<>();

        // Chequeamos factor 2 por separado para optimizar
        if (temp % 2 == 0) {
            primes.add(2);            // <-- guardamos el primo "2" (distinto)
            while (temp % 2 == 0) {   // removemos todas las potencias de 2
                temp /= 2;
            }
        }
        // Recorremos impares desde 3 hasta sqrt(temp)
        for (int f = 3; (long) f * f <= temp; f += 2) {
            if (temp % f == 0) {
                primes.add(f);        // <-- guardamos el primo "f" (distinto)
                while (temp % f == 0) {
                    temp /= f;        // removemos su potencia
                }
            }
        }
        // Si quedó un primo > 1, es el último factor
        if (temp > 1) {
            primes.add(temp);         // <-- primo distinto final
        }

        // --- 2) K es el número de primos distintos de N (ω(N)) ---
        int K = primes.size();

        // --- 3) Construimos consultas óptimas ---
        // Para cada primo p, consultamos el prefijo de longitud N/p: (L=1, R=N/p).
        // Esto garantiza alineación y evita rotaciones.
        List<int[]> queries = new ArrayList<>();
        for (int p : primes) {
            int len = N / p;          // longitud candidata = N/p  (máximo divisor ≤ N/2 asociado a p)
            queries.add(new int[]{1, len});
        }

        // --- 4) Salida ---
        StringBuilder out = new StringBuilder();
        out.append(K).append('\n');
        for (int[] q : queries) {
            out.append(q[0]).append(' ').append(q[1]).append('\n');
        }
        System.out.print(out.toString());
    }

    // Scanner rápido para evitar overhead en I/O
    static class FastScanner {
        BufferedInputStream in;
        byte[] buffer = new byte[1 << 16];
        int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = new BufferedInputStream(is); }
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
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * sgn;
        }
    }
}
