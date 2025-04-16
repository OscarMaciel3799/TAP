import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String valores="";

        for (int i = 0; i < 8; i++) {
            valores+= scanner.next();
        }
        if(valores.contains("9")){
            System.out.println("F");
        }else {
            System.out.println("S");
        }
    }
}