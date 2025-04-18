import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String nombre=scanner.next();
        if (nombre.charAt(nombre.length()-1)=='a' || nombre.charAt(nombre.length()-1)=='o'){
            System.out.println(nombre.substring(0,nombre.length()-1)+"ic"+nombre.substring(nombre.length()-1,nombre.length()));
        }
    }
}