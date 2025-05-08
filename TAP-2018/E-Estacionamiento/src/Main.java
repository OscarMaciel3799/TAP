import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer el valor de M
        int M = scanner.nextInt();
        double[] probabilidades = new double[2 * M];

        // Leer las probabilidades
        for (int i = 0; i < 2 * M; i++) {
            probabilidades[i] = scanner.nextDouble();
        }

        double distanciaEsperada = 0.0;

        // Calcular la distancia esperada
        for (int i = 1; i <= M; i++) {
            distanciaEsperada += i * probabilidades[i - 1]; // Oeste
            distanciaEsperada += i * probabilidades[M + i - 1]; // Este
        }

        // Imprimir el resultado con 6 decimales
        System.out.printf("%.6f%n", distanciaEsperada);