import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] alturas = new int[N];

        for (int i = 0; i < N; i++) {
            alturas[i] = scanner.nextInt();
        }

        Map<Integer, Integer> flechas = new HashMap<>();
        int totalFlechas = 0;

        for (int h : alturas) {
            if (flechas.getOrDefault(h, 0) > 0) {
                flechas.put(h, flechas.get(h) - 1);
                flechas.put(h - 1, flechas.getOrDefault(h - 1, 0) + 1);
            } else {
                totalFlechas++;
                flechas.put(h - 1, flechas.getOrDefault(h - 1, 0) + 1);
            }
        }
        System.out.println(totalFlechas);
    }
}