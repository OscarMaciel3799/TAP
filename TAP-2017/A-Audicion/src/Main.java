import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] escala={"DO","DO#","RE", "RE#", "MI" , "FA", "FA#", "SOL" , "SOL#",
        "LA", "LA#","SI" };
        Scanner scanner=new Scanner(System.in);
        int corrimiento=scanner.nextInt();
        String nota=scanner.next();

        for (int i = 0; i < escala.length; i++) {
            if(nota.equals(escala[i])){
                if(i-corrimiento<0){
                    System.out.println(escala[escala.length+(i-corrimiento)]);
                    break;
                } else {
                    System.out.println(escala[i-corrimiento]);
                    break;
                }
            }
        }
    }
}