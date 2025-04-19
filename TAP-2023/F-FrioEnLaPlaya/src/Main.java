import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int N= scanner.nextInt();
        int W= scanner.nextInt();
        int L= scanner.nextInt();
        int Tx= scanner.nextInt();
        int Ty= scanner.nextInt();

        double[] lanzamientosA=new double[N];
        double[] lanzamientosR=new double[N];

        for (int i = 0; i < N; i++) {
            int Lx= scanner.nextInt();
            int Ly= scanner.nextInt();
            double d=Math.sqrt(Math.pow((Tx-Lx),2)+ Math.pow((Ty-Ly),2));
            lanzamientosA[i]=d;
        }
        for (int i = 0; i < N; i++) {
            int Lx= scanner.nextInt();
            int Ly= scanner.nextInt();
            double d=Math.sqrt(Math.pow((Tx-Lx),2)+ Math.pow((Ty-Ly),2));
            lanzamientosR[i]=d;
        }
        Arrays.sort(lanzamientosA);
        Arrays.sort(lanzamientosR);
        int puntos=0;
        int equipo=0;
        if (lanzamientosA[0] < lanzamientosR[0]){
            for (int i = 0; i < N; i++) {
                if (lanzamientosR[0] < lanzamientosA[i]){
                    puntos=i;
                    break;
                }
            }
        }else {
            for (int i = 0; i < N; i++) {
                equipo=1;
                if (lanzamientosA[0] < lanzamientosR[i]){
                    puntos=i;
                    break;
                }
            }
        }
        if(puntos>0){
            if (equipo==0){
                System.out.println("A "+puntos);
            }else{
                System.out.println("R "+puntos);
            }
        }else {
            if (equipo==0){
                System.out.println("A "+N);
            }else{
                System.out.println("R "+N);
            }
        }
    }
}