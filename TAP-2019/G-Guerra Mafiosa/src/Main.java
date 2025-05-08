import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        long n= scanner.nextInt();
        ArrayList<Integer> listaBandas =new ArrayList<>();
        for (long i = 0; i < n; i++) {
            listaBandas.add(scanner.nextInt());
        }
        long galtier= listaBandas.get(0);
        listaBandas.remove(0);
        String sobrevive="SI";
        Collections.sort(listaBandas);
        long i=0;

        long cantidadBandas;
        long miembrosPerdidos;
        long miembrosEnemigos;

        if (n==1){
            sobrevive="SI";
        }else if (n==2){
            if (galtier>=listaBandas.get(0)){
                sobrevive="SI";
            }else{
                sobrevive="NO";
            }
        }else{
            //cada iteracion es mi turno en el que elimino toda una banda, por lo que:
            //para el caso 3 4 2 2 , si elimino a la banda de 2, en ese momento perdi 3 siguiendo lo siguiente:
            /*
            4 2 2
            4 1 2 //ataco
            2 1 2 //me atacan
            2 0 2 //ataco
            1 0 2 //me atacan => en este punto ya elimine a la banda y me atacaron
             */
            for (int j = 0; j <listaBandas.size() ; j++) {
                miembrosEnemigos= listaBandas.get(j)-1;
                if (miembrosEnemigos>0){ // al menos un miembro
                    if ((listaBandas.size()-j)>1){
                        cantidadBandas= listaBandas.size()-j;
                        miembrosPerdidos=cantidadBandas*miembrosEnemigos+(cantidadBandas-1);
                    }else{
                        miembrosPerdidos=miembrosEnemigos;
                    }
                }else{ // ningun miembro
                    if ((listaBandas.size()-j)>1){
                        cantidadBandas=listaBandas.size()-j-1;
                        miembrosPerdidos=cantidadBandas;
                    }else{
                        break;
                    }
                }
                galtier-=miembrosPerdidos;
                if (galtier<=0){
                    sobrevive="NO";
                    break;
                }
            }
        }
        System.out.println(sobrevive);
    }
}