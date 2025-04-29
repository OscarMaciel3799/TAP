
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long D = sc.nextLong();
        int M = sc.nextInt();
        long D1 = sc.nextLong();
        int N = sc.nextInt();
        long D2 = sc.nextLong();

        int usados = 0;
        long dist = D;

        if(D>D1 && dist>D2){
            System.out.println(-1);
            return;
        }

        long distancia1=D2;
        int cant1=N;
        long distancia2=D1;
        int cant2=M;
        if(D1>D2){
            distancia1=D1;
            cant1=M;
            distancia2=D2;
            cant2=N;
        }

        while (dist > 0 && distancia1 > dist && cant1 > 0) {
                usados++;
                dist -= distancia1-dist;
                cant1--;
        }
        while (dist > 0 && distancia2 > dist && cant2 > 0) {
            usados++;
            dist -= distancia2-dist;
            cant2--;
        }
        System.out.println(dist <= 0 ? usados : -1);
    }
}