import java.util.ArrayList;

public class Pr2 {
    public static void main(String[] args) throws Exception {
        int tam = 10;
        int tamPoblacion = 50;
        String nombreAlg;
        int nAlg;
        double rmin, rmax;
        long semilla;
        int evaluaciones = 10000;
        int op = 1;
        double probMutacion = 0.01;
        double probCruce = 0.7;
        double alpha = 0.5;
        ArrayList<Double> sol = new ArrayList<Double>();

        LeerArchivo leer = new LeerArchivo("Ackley.txt");
        tam = leer.getTam();
        nombreAlg = leer.getNombreAlg();
        rmin = leer.getRmin();
        rmax = leer.getRmax();
        semilla = leer.getSemilla()[0];
        evaluaciones = leer.getEvaluaciones();
        nAlg = leer.getnAlg();

        double resultado = EvBLX.EvaBLX(tamPoblacion, tam, evaluaciones, sol, rmin, rmax,
                probMutacion, probCruce, nAlg, alpha, semilla);
        //double resultado = EvM.EvMedia(tamPoblacion, tam, evaluaciones, sol, rmin, rmax,
        //        probMutacion, probCruce, nAlg, semilla);
        System.out.println(resultado);


    }
}

