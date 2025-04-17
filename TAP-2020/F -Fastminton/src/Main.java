import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String entrada= scanner.next();

        int[] puntos= {0,0};
        int[] juegos= {0,0};
        int jugador=0;
        for (int i = 0; i < entrada.length(); i++) {
            char caracter = entrada.charAt(i);
            switch (caracter){
                case 'S':
                    puntos[jugador]++;
                    break;
                case 'R':
                    jugador=1-jugador;
                    puntos[jugador]++;
                    break;
                case 'Q':
                    if (juegos[0] == 2 || juegos[1] == 2) {
                        System.out.println((juegos[0] == 2 ? juegos[0] + " (winner) - " : juegos[0] + " - ") + (juegos[1] == 2 ? juegos[1] + " (winner)" : juegos[1]));
                    } else {
                        String izq = juegos[0] + (jugador == 0 ? " (" + puntos[0] + "*)" : " (" + puntos[0] + ")");
                        String der = juegos[1] + (jugador == 1 ? " (" + puntos[1] + "*)" : " (" + puntos[1] + ")");
                        System.out.println(izq + " - " + der);
                    }
                    break;

            }

            if((puntos[0] == 10 ) || (puntos[0]>= 5 && puntos[0]>=puntos[1]+2)){
                juegos[0]+=1;
                puntos[0]=0;
                puntos[1]=0;
            } else if ((puntos[1] == 10 ) || (puntos[1]>= 5 && puntos[1]>=puntos[0]+2)) {
                juegos[1]+=1;
                puntos[0]=0;
                puntos[1]=0;
            }

        }
        scanner.close();
    }
}