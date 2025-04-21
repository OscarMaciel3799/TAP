import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int T=0;
        int U=0;
        int P=0;
        int A=0;
        String guirnalda=scanner.next();
        for (int i = 0; i < guirnalda.length(); i++) {

            if(guirnalda.charAt(i)=='T'){
                T++;
            } else if(guirnalda.charAt(i)=='A'){
                A++;
            }else if(guirnalda.charAt(i)=='U'){
                U++;
            }else if(guirnalda.charAt(i)=='P'){
               P++;
            }

        }
        int cantidad=0;
        if (T<=P){
            cantidad=T;
        }else {
            cantidad=P;
        }
        if (U+A<cantidad){
            cantidad=U+A;
        }
        System.out.println(cantidad);
    }
}