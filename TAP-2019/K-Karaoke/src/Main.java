import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Cantante {
        int inicio, fin;

        Cantante(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Cantante> danielistas = new ArrayList<>();
        List<Cantante> javieristas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tipo = sc.next();
            int si = sc.nextInt();
            int ei = sc.nextInt();

            if (tipo.equals("D")) {
                danielistas.add(new Cantante(si, ei));
            } else {
                javieristas.add(new Cantante(si, ei));
            }
        }

        danielistas.sort(Comparator.comparingInt(c -> c.inicio));
        javieristas.sort(Comparator.comparingInt(c -> c.inicio));

        int maxInterseccion = 0;
        int i = 0, j = 0;

        // Recorremos ambas listas para encontrar la intersección máxima
        while (i < danielistas.size() && j < javieristas.size()) {
            Cantante d = danielistas.get(i);
            Cantante jv = javieristas.get(j);

            int inicio = Math.max(d.inicio, jv.inicio);
            int fin = Math.min(d.fin, jv.fin);

            if (inicio < fin) {
                maxInterseccion = Math.max(maxInterseccion, fin - inicio);
            }

            // Avanzamos el puntero del cantante que tiene el rango de fin menor
            if (d.fin < jv.fin) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(maxInterseccion);
    }
}