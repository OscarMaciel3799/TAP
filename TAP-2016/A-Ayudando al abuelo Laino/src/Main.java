import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String obj=scanner.next();
        if(obj.contains("i")){
            System.out.println("N");
        }else {
            System.out.println("S");
        }
    }
}