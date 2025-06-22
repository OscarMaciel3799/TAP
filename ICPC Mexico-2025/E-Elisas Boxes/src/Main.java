import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int indice=-1;
        List<Integer> boxes = new ArrayList();
        for (int i = 0; i < n; i++) {
            int box = scanner.nextInt();
            boxes.add(box);
        }
        for (int i = 0; i < n; i++) {
            if (boxes.get(i) >= m) {
                indice = i+1;
                break;
            }
        }
        System.out.println(indice);
    }
}