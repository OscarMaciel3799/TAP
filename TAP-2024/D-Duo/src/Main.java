import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int[] puntos=new int[3];
        for (int i = 0; i < 3; i++) {
            puntos[i]= scanner.nextInt();
        }
        if(puntos[0]>=(puntos[1]+puntos[2]) || puntos[1]>=(puntos[0]+puntos[2]) || puntos[2]>=(puntos[1]+puntos[0])){
            System.out.println("S");
        }else {
            System.out.println("N");
        }
    }
}