import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        int[] cartasE=new int[3];

        for (int i = 0; i < 3; i++) {
            cartasE[i]=scanner.nextInt();
        }
        int puntos=0;
        for (int i = 0; i < 3; i++) {

            if (cartasE[i]==1 || cartasE[i]==2 || cartasE[i]==3){
                puntos++;
            }
        }
        Arrays.sort(cartasE);
        if(((cartasE[0]==1 || cartasE[0]==2) && cartasE[1]==4 && cartasE[2]==5) ){
            puntos=2;
        }
        if (1<puntos){
            System.out.println("S");
        }else{
            System.out.println("N");
        }
    }
}