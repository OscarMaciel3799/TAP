import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        List<List<Integer>> dependientes = new ArrayList<>();
        for (int i = 0; i <= N; i++) dependientes.add(new ArrayList<>());

        int[] prerequisitos = new int[N + 1];

        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            dependientes.get(A).add(B);
            prerequisitos[B]++;
        }

        int[] aprobadas = new int[N];
        for (int i = 0; i < N; i++) aprobadas[i] = sc.nextInt();

        boolean[] yaAprobadas = new boolean[N + 1];
        boolean[] registradas = new boolean[N + 1];

        int registradasTotales = 0;

        for (int i = 0; i < N; i++) {
            int actual = aprobadas[i];
            yaAprobadas[actual] = true;

            Queue<Integer> queue = new LinkedList<>();

            if (prerequisitos[actual] == 0) {
                queue.add(actual);
            }

            while (!queue.isEmpty()) {
                int materia = queue.poll();
                if (registradas[materia]) continue;
                registradas[materia] = true;
                registradasTotales++;

                for (int dep : dependientes.get(materia)) {
                    prerequisitos[dep]--;
                    if (yaAprobadas[dep] && prerequisitos[dep] == 0) {
                        queue.add(dep);
                    }
                }
            }

            System.out.println(registradasTotales);
        }
    }
}
