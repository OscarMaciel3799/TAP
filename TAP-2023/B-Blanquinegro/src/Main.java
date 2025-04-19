import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int cantidad=0;

        for (int i = 0; i < N; i++) {
            String cadena=scanner.next();
            int conteo=0;
            int j=0;
            while (j < N){
                if (cadena.charAt(j)=='N'){
                    conteo++;
                    j++;
                }else {
                    cantidad+=(conteo/2);
                    j++;
                    conteo=0;
                }
            }
            cantidad+=(conteo/2);
        }
        System.out.println(cantidad);
    }
}