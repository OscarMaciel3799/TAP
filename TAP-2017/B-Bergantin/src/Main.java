import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] pedidos = new int[N];
        int[] cofres = new int[N];

        for (int i = 0; i < N; i++) {
            pedidos[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            cofres[i] = sc.nextInt();
        }

        System.out.println(contarFormas(N, pedidos, cofres));
    }

    public static int contarFormas(int N, int[] pedidos, int[] cofres) {
        Arrays.sort(pedidos);
        Arrays.sort(cofres);

        long resultado = 1;
        int j = N - 1;

        for (int i = N - 1; i >= 0; i--) {
            while (j >= 0 && cofres[j] >= pedidos[i]) {
                j--;
            }
            int disponibles = N - 1 - j;
            int usados = N - 1 - i;
            int opciones = disponibles - usados;

            if (opciones <= 0) return 0;

            resultado = (resultado * opciones) % MOD;
        }

        return (int) resultado;
    }
}