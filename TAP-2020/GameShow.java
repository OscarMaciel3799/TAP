import java.util.Scanner;

public class GameShow {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int cantValor= scanner.nextInt();
        int inicial=100;
        int mayor=100;
        for(int i=0; i<cantValor;i++){
            int actual= scanner.nextInt();
            inicial+=actual;
            mayor=Math.max(mayor,inicial);
        }
        System.out.println(mayor);
    }
}
