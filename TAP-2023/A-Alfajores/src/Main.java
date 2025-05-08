import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static long[] tree;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long[] A = new long[N];
        long[] E = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) E[i] = Long.parseLong(st.nextToken());

        // Inicializamos el segment tree con valores mínimos
        buildSegmentTree(E);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            long rem = A[i];
            int pos = 0;

            while (pos < M) {
                int next = findNext(pos, rem);
                if (next == -1) break; // no hay más oficinas válidas
                rem %= E[next];
                if (rem == 0) break;
                pos = next + 1;
            }

            sb.append(rem).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    // Se crea el segment tree para RMQ (mínimos)
    private static void buildSegmentTree(long[] E) {
        size = 1;
        while (size < M) size <<= 1;
        tree = new long[2 * size];
        Arrays.fill(tree, Long.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            tree[size + i] = E[i];
        }
        for (int i = size - 1; i > 0; i--) {
            tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
        }
    }

    // Se busca el índice más a la izquierda ≥ pos tal que E[pos] <= rem
    private static int findNext(int pos, long rem) {
        return query(1, 0, size - 1, pos, M - 1, rem);
    }

    // Query para encontrar el primer índice con E[i] <= rem
    private static int query(int node, int l, int r, int ql, int qr, long rem) {
        if (r < ql || l > qr || tree[node] > rem) return -1;

        if (l == r) return l;

        int mid = (l + r) / 2;
        int left = query(2 * node, l, mid, ql, qr, rem);
        if (left != -1) return left;
        return query(2 * node + 1, mid + 1, r, ql, qr, rem);
    }
}