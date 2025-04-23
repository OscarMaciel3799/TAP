import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        podemos ver que hay un ciclo que se repite
        46 24832612 24832612 a partir del 3er caracter los sig 8 se repiten
        */
        Scanner scanner=new Scanner(System.in);
        int X= scanner.nextInt();
        int Y= scanner.nextInt();
        int Z= scanner.nextInt();
        int W= scanner.nextInt();

        System.out.println(obtenerDigito(X)+obtenerDigito(Y)+obtenerDigito(Z)+obtenerDigito(W));

    }

    private static String obtenerDigito(int pos) {
        int[] ciclo = {2, 4, 8, 3, 2, 6, 1, 2};
        if (pos == 1) return "4";
        if (pos == 2) return "6";
        return String.valueOf(ciclo[(pos - 3) % 8]);
    }
}