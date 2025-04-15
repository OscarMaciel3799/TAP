import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int A=0;
        int P=0;

        for(int i=0;i<2;i++){
            A=A+scanner.nextInt();
            P=P+scanner.nextInt();
        }
        if(A<P){
            System.out.println("P");
        } else if (P<A) {
            System.out.println("A");
        }else{
            System.out.println("D");
        }

    }
}