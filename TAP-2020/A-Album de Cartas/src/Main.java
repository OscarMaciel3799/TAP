import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        int t = b - a + 1;
        double[] dp = new double[10000001]; // Se debe crear un arreglo lo suficientemente grande
        double mult = 1;

        if (a == 0) {
            a = 1;
            mult = (double) t / (double) (t - 1);
        }

        int windowA = 0;
        int windowB = 0;
        double windowSum = 0;

        for (int i = 1; i <= n; i++) {
            while (windowB < i - a + 1) {
                windowSum += dp[windowB];
                windowB++;
            }
            while (windowA < i - b) {
                windowSum -= dp[windowA];
                windowA++;
            }
            dp[i] = mult * (1.0 + (1.0 / (double) t) * windowSum);
        }

        double ret = dp[n];
        System.out.printf("%.5f\n", ret);
    }
}
