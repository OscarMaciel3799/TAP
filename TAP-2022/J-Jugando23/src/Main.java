import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] cartasMazo = new int[13];
        Arrays.fill(cartasMazo, 4);

        int r = Integer.parseInt(br.readLine());

        int sumaJuan = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            int valor = Integer.parseInt(st.nextToken());
            cartasMazo[valor - 1]--;
            sumaJuan += getValorCarta(valor);
        }

        int sumaMaria = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            int valor = Integer.parseInt(st.nextToken());
            cartasMazo[valor - 1]--;
            sumaMaria += getValorCarta(valor);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            int valor = Integer.parseInt(st.nextToken());
            cartasMazo[valor - 1]--;
            int v = getValorCarta(valor);
            sumaJuan += v;
            sumaMaria += v;
        }

        int respuesta = -1;

        for (int i = 1; i <= 13; i++) {
            if (cartasMazo[i - 1] == 0) continue;

            int valorCarta = getValorCarta(i);
            int nuevaMaria = sumaMaria + valorCarta;
            int nuevaJuan = sumaJuan + valorCarta;

            if (nuevaMaria > 23) continue;

            if (nuevaJuan > 23 || nuevaMaria == 23) {
                respuesta = i;
                break;
            }
        }

        System.out.println(respuesta);
    }

    static int getValorCarta(int carta) {
        return (carta >= 11) ? 10 : carta;
    }
}