import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int M=scanner.nextInt();
        int X=scanner.nextInt();
        int Y=scanner.nextInt();
        double notaA= ( (double) X/N+ (double) Y/M)/2;
        double notaB=(double) (X+Y)/(M+N);
        notaA= Math.round(notaA * 1000000000000000.0) ;
        notaB= Math.round(notaB * 1000000000000000.0);

        if (notaA>notaB){
            System.out.println("A");
        } else if (notaB>notaA) {
            System.out.println("B");
        }else{
            System.out.println("C");
        }
        scanner.close();
    }
}