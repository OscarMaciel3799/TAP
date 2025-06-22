import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        String s=scanner.next();
        int n= scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String p= scanner.next();
            String msj="Yes";
            if(p.length()%s.length()==0){
                for (int j = 0; j < p.length(); j++) {
                    if (p.charAt(j) != s.charAt(j % s.length())) {
                        msj = "No";
                        break;
                    }
                }
            }else {
                msj="No";
            }
            System.out.println(msj);
        }
    }
}