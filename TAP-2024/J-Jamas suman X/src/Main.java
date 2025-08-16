import java.io.*;
import java.util.*;

/**
 * Never Add Up to X
 * Técnicas:
 * - Ordenamiento O(N log N)
 * - Partición por X/2: L (< X/2), H (= X/2 si X par), R (> X/2)
 * - Construcción por "slots": si base tiene k elementos, hay k+1 huecos para colocar H sin adyacencias H-H
 * - Si no hay H y el límite L|R es peligroso, intentamos "mover" el primer R o el último L
 *   (si sólo hay dos valores a y X-a, es imposible)
 */
public class Main {
    // Fast I/O
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
        long nextLong() throws IOException {
            int c; do c = read(); while (c <= ' ' && c != -1);
            long sgn = 1;
            if (c == '-') { sgn = -1; c = read(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sgn;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        long X = fs.nextLong();
        int[] B = new int[N];
        for (int i = 0; i < N; i++) B[i] = fs.nextInt();
        Arrays.sort(B);

        // Partición por X/2
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> H = new ArrayList<>(); // b == X/2 (si X es par)
        ArrayList<Integer> R = new ArrayList<>();
        for (int v : B) {
            long t = 2L * v;
            if (t < X) L.add(v);
            else if (t > X) R.add(v);
            else H.add(v); // t == X
        }

        List<Integer> ans = buildAnswer(L, H, R, X, true);
        if (ans == null) ans = buildAnswer(L, H, R, X, false);

        if (ans == null || !isValid(ans, X)) {
            System.out.println("*");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(ans.get(i));
        }
        System.out.println(sb.toString());
    }

    /** Intenta construir con base = (leftFirst ? L+R : R+L) y coloca H usando slots. */
    static List<Integer> buildAnswer(List<Integer> L, List<Integer> H, List<Integer> R, long X, boolean leftFirst) {
        // 1) Construcción de la base A y posición de límite L|R (si existe)
        List<Integer> A = new ArrayList<>(L.size() + R.size());
        int boundaryPos = -1; // índice del elemento ANTES del límite
        if (leftFirst) {
            A.addAll(L);
            if (!L.isEmpty() && !R.isEmpty()) boundaryPos = L.size() - 1;
            A.addAll(R);
        } else {
            A.addAll(R);
            if (!R.isEmpty() && !L.isEmpty()) boundaryPos = R.size() - 1;
            A.addAll(L);
        }

        int k = A.size();

        // 2) Si no hay base (sólo H):
        if (k == 0) {
            if (H.size() <= 1) return new ArrayList<>(H);
            return null; // 2 o más H => habría H-H consecutivos que suman X
        }

        // 3) ¿El límite es peligroso?
        boolean boundaryDanger = false;
        int boundarySlot = -1; // slot entre boundaryPos y boundaryPos+1
        if (boundaryPos != -1) {
            long sum = (long)A.get(boundaryPos) + (long)A.get(boundaryPos + 1);
            boundaryDanger = (sum == X);
            boundarySlot = boundaryPos + 1; // slots numerados 0..k
        }

        int h = H.size();
        // 4) Caso sin H: arreglar límite o imposible
        if (h == 0) {
            if (!boundaryDanger) return A; // ya sirve

            // Intentar mover elementos para romper el límite
            ArrayList<Integer> out = new ArrayList<>(k);
            if (leftFirst) {
                // base = L + R
                if (fixBoundaryByRFirst(L, R, X, out)) return out;
                if (fixBoundaryByLLast(L, R, X, out)) return out;
            } else {
                // base = R + L
                if (fixBoundaryByRLast(R, L, X, out)) return out;
                if (fixBoundaryByLFirst(R, L, X, out)) return out;
            }
            return null; // probablemente sólo {a, X-a}
        }

        // 5) Con H: usar "slots" (0..k). Condición necesaria y suficiente: h <= k+1
        if (h > k + 1) return null;

        boolean[] place = new boolean[k + 1]; // a lo sumo 1 H por slot
        // Si el límite es peligroso, forzamos un H en ese slot
        if (boundaryDanger) {
            place[boundarySlot] = true;
            h--;
        }

        // Distribuir los H restantes en otros slots (izq->der)
        for (int s = 0; s <= k && h > 0; s++) {
            if (!place[s]) {
                place[s] = true;
                h--;
            }
        }
        // (si la condición h <= k+1 se cumple, no queda h > 0 aquí)

        // 6) Construir el resultado sin crear H-H consecutivos
        ArrayList<Integer> res = new ArrayList<>(A.size() + H.size());
        // Tomamos H desde el final para O(1); el orden de H no importa
        int hIdx = H.size() - 1;

        for (int s = 0; s <= k; s++) {
            if (place[s]) res.add(H.get(hIdx--)); // H en slot s
            if (s < k) res.add(A.get(s));         // luego el elemento base (si existe)
        }
        return res;
    }

    // ------- Helpers para arreglar el límite cuando no hay H -------

    // base = L + R, límite entre L_last y R_first => elegir otro R_first que no complemente a L_last
    static boolean fixBoundaryByRFirst(List<Integer> L, List<Integer> R, long X, List<Integer> out) {
        if (L.isEmpty() || R.isEmpty()) return false;
        int lastL = L.get(L.size() - 1);
        int pos = -1;
        for (int j = 0; j < R.size(); j++) {
            if ((long)lastL + R.get(j) != X) { pos = j; break; }
        }
        if (pos == -1) return false;
        out.clear();
        out.addAll(L);
        out.add(R.get(pos));
        for (int j = 0; j < R.size(); j++) if (j != pos) out.add(R.get(j));
        return true;
    }

    // base = L + R, intentar mover un L que no complemente al primer R para que quede al final de L
    static boolean fixBoundaryByLLast(List<Integer> L, List<Integer> R, long X, List<Integer> out) {
        if (L.isEmpty() || R.isEmpty()) return false;
        int firstR = R.get(0);
        int pos = -1;
        for (int i = 0; i < L.size(); i++) {
            if ((long)L.get(i) + firstR != X) { pos = i; break; }
        }
        if (pos == -1) return false;
        out.clear();
        for (int i = 0; i < L.size(); i++) if (i != pos) out.add(L.get(i));
        out.add(L.get(pos));
        out.addAll(R);
        return true;
    }

    // base = R + L, intentar mover un R que no complemente al primer L para que quede al final de R
    static boolean fixBoundaryByRLast(List<Integer> R, List<Integer> L, long X, List<Integer> out) {
        if (R.isEmpty() || L.isEmpty()) return false;
        int firstL = L.get(0);
        int pos = -1;
        for (int i = 0; i < R.size(); i++) {
            if ((long)R.get(i) + firstL != X) { pos = i; break; }
        }
        if (pos == -1) return false;
        out.clear();
        for (int i = 0; i < R.size(); i++) if (i != pos) out.add(R.get(i));
        out.add(R.get(pos));
        out.addAll(L);
        return true;
    }

    // base = R + L, elegir un L_first que no complemente al último R
    static boolean fixBoundaryByLFirst(List<Integer> R, List<Integer> L, long X, List<Integer> out) {
        if (R.isEmpty() || L.isEmpty()) return false;
        int lastR = R.get(R.size() - 1);
        int pos = -1;
        for (int j = 0; j < L.size(); j++) {
            if ((long)lastR + L.get(j) != X) { pos = j; break; }
        }
        if (pos == -1) return false;
        out.clear();
        out.addAll(R);
        out.add(L.get(pos));
        for (int j = 0; j < L.size(); j++) if (j != pos) out.add(L.get(j));
        return true;
    }

    // ------- Chequeo lineal de corrección -------
    static boolean isValid(List<Integer> arr, long X) {
        for (int i = 0; i + 1 < arr.size(); i++) {
            if ((long)arr.get(i) + arr.get(i + 1) == X) return false;
        }
        return true;
    }
}
