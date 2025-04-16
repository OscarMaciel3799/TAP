
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Supera el tiempo permitido, buscar solucion más eficiente para grandes volumenes

        Scanner scanner=new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] LN = new int[N];
        int[] LM = new int[M];


        for (int i = 0; i < N; i++) {
            LN[i]=scanner.nextInt();
        }

        for (int i = 0; i < M; i++) {
            LM[i]=scanner.nextInt();
        }

        for(int i=0;i <N;i++){
            for(int j=0; j<M; j++){
                LN[i] %= LM[j];
            }
            System.out.println(LN[i]);
        }
        scanner.close();
    }
}