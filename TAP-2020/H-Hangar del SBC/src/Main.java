import java.util.*;

public class Main {
    static long[] weights;
    static int n, k;
    static long A, B;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        weights = new long[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextLong();
        }
        A = sc.nextLong();
        B = sc.nextLong();

        Arrays.sort(weights);

        // Validamos que cada peso sea al menos el doble del anterior
        for (int i = 1; i < n; i++) {
            if (weights[i] < 2 * weights[i - 1]) {
                System.out.println(0);
                return;
            }
        }

        long count = usingK(k, binary(B)) - usingK(k, binary(A - 1));
        System.out.println(count);
    }

    // Búsqueda binaria para hallar el mayor índice tal que suma de subconjunto <= target
    static long binary(long target) {
        long a = 0, b = 1L << n;
        while (b - a > 1) {
            long mid = (a + b) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (((mid >> i) & 1) == 1) {
                    sum += weights[i];
                    if (sum > target) break;
                }
            }
            if (sum > target) {
                b = mid;
            } else {
                a = mid;
            }
        }
        return b;
    }

    // Contamos cuántos subconjuntos de tamaño k tienen máscara menor que "limit"
    static long usingK(int k, long limit) {
        long res = 0;
        int seen = 0;
        for (int i = 63; i >= 0; i--) {
            if (((limit >> i) & 1) == 1) {
                int remaining = k - seen;
                if (remaining >= 0 && remaining <= i) {
                    res += nCr(i, remaining);
                }
                seen++;
            }
        }
        return res;
    }

    // Combinatoria
    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
