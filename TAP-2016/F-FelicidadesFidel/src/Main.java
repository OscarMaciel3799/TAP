import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();
        String[] palabras=new String[n];
        for (int i = 0; i < n; i++) {
            palabras[i]=scanner.next();
        }
        int total=0;
        boolean d=false;
        for (int i = 0; i < n; i++) {
            String palabra=palabras[i];
            for (int j = 0; j < palabra.length(); j++) {
                if (palabra.charAt(j)=='D' && !d){
                    d=true;
                } else if (palabra.charAt(j)=='R' && d) {
                    total++;
                    d=false;
                }
            }
        }
        System.out.println(total);
    }
}