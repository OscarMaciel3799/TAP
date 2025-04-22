import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        String cadena=scanner.next();
        int si=0;
        int no=0;
        int indeciso=0;
        int i=0;

        while ( i< N ){
            char caracter=cadena.charAt(i);
            int length=0;
            if(caracter=='P'){
                while (i< N && cadena.charAt(i)==caracter){
                    length++;
                    i++;
                }
                si+=length;
            } else if (caracter=='N') {
                while (i< N && cadena.charAt(i)==caracter){
                    length++;
                    i++;
                }
                no+=length;
            } else if (caracter=='I') {
                while (i< N && cadena.charAt(i)==caracter){
                    length++;
                    i++;
                }
                indeciso+=length;
            }
        }

            if(no>si || no>=si+indeciso){
                System.out.println("NO");
            } else if (si>no) {
                System.out.println("SI");
            }else if(si+indeciso>no){
                System.out.println("INDECISOS");
            }

    }
}