import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N= scanner.nextInt();

        int[][] C=new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                C[i][j]=scanner.nextInt();
            }
        }
        String apto="SI";
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                double dx=C[i][0]-C[j][0];
                double dy=C[i][1]-C[j][1];
                double d=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
                boolean tang=((d>Math.abs((C[i][2]-C[j][2]))) && (d<(C[i][2]+C[j][2])));
                boolean tInt=(d==(C[i][2]+C[j][2]));
                boolean tExt=(d==Math.abs((C[i][2]-C[j][2])));
                if( tang || tInt || tExt){
                    apto="NO";
                    break;
                }
            }
        }
        System.out.println(apto);
    }
}