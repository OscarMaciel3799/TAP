import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long[] values = new long[N];
        for (int i = 0; i < N; i++) {
            values[i] = sc.nextLong();
        }

        // 1. Ordenamos la lista (O(N log N))
        Arrays.sort(values);

        // Variable para almacenar la máxima ganancia encontrada
        long maxProfit = Long.MIN_VALUE;

        // Función lambda para calcular la ganancia dada una combinación (a,b,c)
        // Usamos la fórmula original
        java.util.function.Function<long[], Long> profit = dims ->
                dims[0]*dims[0] + dims[1]*dims[1] + dims[2]*dims[2]
                        - (dims[0]*dims[1] + dims[1]*dims[2] + dims[2]*dims[0]);

        // 2. Probamos combinaciones usando valores extremos
        // Combinamos los más pequeños y los más grandes
        int[] idx = {0, 1, N-1, N-2}; // índices a probar para extremos
        for (int i : idx) {
            if (i < 0 || i >= N) continue;
            for (int j : idx) {
                if (j < 0 || j >= N) continue;
                for (int k : idx) {
                    if (k < 0 || k >= N) continue;
                    long[] dims = {values[i], values[j], values[k]};
                    long currentProfit = profit.apply(dims);
                    if (currentProfit > maxProfit) {
                        maxProfit = currentProfit;
                    }
                }
            }
        }

        // 3. Imprimimos la máxima ganancia encontrada
        System.out.println(maxProfit);
    }
}
