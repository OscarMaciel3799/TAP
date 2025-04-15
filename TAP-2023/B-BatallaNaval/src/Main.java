import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int[][] matriz = new int[10][10];
        for(int i=0; i<10;i++){
            for(int j=0; j<10;j++){
                matriz[i][j]=0;
            }
        }
        int fila;
        int columna;
        String salida="Y";
        for(int i=0; i<N;i++){
            int D= scanner.nextInt();
            int L= scanner.nextInt();
            int R= scanner.nextInt()-1;
            int C= scanner.nextInt()-1;

            for (int j = 0; j < L; j++) {
                fila = R + (D == 1 ? j : 0);
                columna = C + (D == 0 ? j : 0);
                if (fila >= 10 || columna >= 10 || fila < 0 || columna < 0) {
                    salida = "N";
                    break;
                }
                if (matriz[fila][columna]==1) {
                    salida = "N";
                    break;
                }
                matriz[fila][columna]=1;
            }
        }
        System.out.println(salida);

    }

}