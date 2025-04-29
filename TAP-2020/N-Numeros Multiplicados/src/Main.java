import java.math.BigInteger;
import java.util.*;

public class Main {
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(), N = sc.nextInt(), K = sc.nextInt();
        BigInteger[] C = new BigInteger[N];
        for (int i = 0; i < N; i++) C[i] = new BigInteger(sc.next());

        List<Map<Integer, Integer>> connections = new ArrayList<>();
        for (int i = 0; i < N; i++) connections.add(new HashMap<>());

        for (int i = 0; i < K; i++) {
            int m = sc.nextInt() - 1, n = sc.nextInt() - 1, d = sc.nextInt();
            connections.get(n).put(m, connections.get(n).getOrDefault(m, 0) + d);
        }

        Set<BigInteger> allPrimes = new HashSet<>();
        for (BigInteger ci : C) {
            Map<BigInteger, Integer> factors = new HashMap<>();
            factor(ci, factors);
            allPrimes.addAll(factors.keySet());
        }

        List<BigInteger> primesList = new ArrayList<>(allPrimes);
        Collections.sort(primesList);
        BigInteger[] result = new BigInteger[M];

        for (int m = 0; m < M; m++) {
            for (BigInteger p : primesList) {
                boolean ok = true;
                for (int n = 0; n < N; n++) {
                    Map<Integer, Integer> conn = connections.get(n);
                    if (conn.containsKey(m)) {
                        int d = conn.get(m);
                        if (!C[n].mod(p.pow(d)).equals(BigInteger.ZERO)) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    result[m] = p;
                    primesList.remove(p);
                    break;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            System.out.print(result[i]);
            if (i < M - 1) System.out.print(" ");
        }
    }

    static void factor(BigInteger n, Map<BigInteger, Integer> factors) {
        if (n.compareTo(BigInteger.ONE) <= 0) return;
        if (n.isProbablePrime(20)) {
            factors.put(n, factors.getOrDefault(n, 0) + 1);
            return;
        }
        BigInteger d = pollardRho(n);
        factor(d, factors);
        factor(n.divide(d), factors);
    }

    static BigInteger pollardRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return BigInteger.TWO;
        BigInteger x = new BigInteger(n.bitLength(), rand).mod(n);
        BigInteger y = x;
        BigInteger c = new BigInteger(n.bitLength(), rand).mod(n);
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            d = x.subtract(y).abs().gcd(n);
            if (d.equals(n)) return pollardRho(n);
        }
        return d;
    }
}