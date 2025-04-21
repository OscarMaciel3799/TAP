import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        int n= scanner.nextInt();

        int[] partidos=new int[n];
        for (int i = 0; i < n; i++) {
            partidos[i]= scanner.nextInt();
        }
        int puntos=0;
        for (int i = 0; i <n ; i++) {
            if (i>1){
                if(partidos[i]==1 && partidos[i-1]==1 && partidos[i-2]==1){
                    puntos+=2;
                } else if (partidos[i]==1) {
                    puntos++;
                } else if (partidos[i]==0) {
                    puntos--;
                }
            }else {
                if (partidos[i]==1) {
                    puntos++;
                } else if (partidos[i]==0) {
                    puntos--;
                }
            }
        }
        System.out.println(puntos);
    }
}