import java.io.*;
import java.util.*;

public class Alfajores {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // viajes
        int M = Integer.parseInt(st.nextToken()); // oficinas

        long[] A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        long[] E = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            E[i] = Long.parseLong(st.nextToken());
        }

        // Optimizar el % para no recalcular cada vez
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            long remaining = A[i];
            for (int j = 0; j < M; j++) {
                if (E[j] > 1) {
                    remaining %= E[j];
                } else {
                    remaining = 0;
                    break;
                }
            }
            sb.append(remaining).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}


