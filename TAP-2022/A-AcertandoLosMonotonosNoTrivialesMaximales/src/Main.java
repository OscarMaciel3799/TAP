import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String s = scanner.next();

        int countA = 0;
        int i = 0;

        while (i < N) {
            char currentChar = s.charAt(i);
            int length = 0;
            if(currentChar == 'a'){
                while (i < N && s.charAt(i) == currentChar) {
                    length++;
                    i++;
                }
                if (length >= 2) {
                    countA += length;
                }
            }
            i++;
        }
        System.out.println(countA);
        scanner.close();
    }
}