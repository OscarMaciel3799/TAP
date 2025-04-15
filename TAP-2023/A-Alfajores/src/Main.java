import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
            int resultado=LN[i];
            for(int j=0; j<M; j++){
                resultado-=LM[j]*(resultado/LM[j]);
            }
            System.out.println(resultado);
        }
        scanner.close();
    }
}