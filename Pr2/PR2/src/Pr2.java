import java.util.ArrayList;

public class Pr2 {
    public static void main(String[] args) throws Exception {
        int tam = 10;
        int tamPoblacion = 50;
        String nombreAlg;
        String algoritmoEjecucion;
        int nAlg;
        double rmin, rmax;
        long semilla;
        int evaluaciones = 10000;
        int op = 0;
        double probMutacion = 0.01;
        double probCruce = 0.7;
        double alpha = 0.5;
        ArrayList<Double> sol = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> observations = new ArrayList<ArrayList<Double>>();
        double resultado = 0;
        int tipo;
        boolean ficheroTexto = false;


        Funciones.cargarDaido("daido-tra.dat", observations);
        LeerArchivo leer = new LeerArchivo();
        leer.leerConfig();

        tam = leer.getTam();
        nombreAlg = leer.getNombreAlg();
        rmin = leer.getRmin();
        rmax = leer.getRmax();
        semilla = leer.getSemilla();
        evaluaciones = leer.getEvaluaciones();
        nAlg = leer.getnAlg();
        algoritmoEjecucion = leer.getAlgoritmoEjecucion();
        tipo = leer.getTipo();
        ficheroTexto = leer.isFicheroTexto();

        switch (algoritmoEjecucion) {
            case "EvM":
                op = 1;
                break;
            case "EvBLX":
                op = 2;
                break;
            case "EvD":
                op = 3;
                break;
        }

        if (ficheroTexto) {
            if (tipo == 1)
                op = 4;
            if (tipo == 2)
                op = 5;
        }


        EscribirArchivo escribe = new EscribirArchivo(nombreAlg, tam, rmin, rmax, semilla,
                evaluaciones, nAlg, op, algoritmoEjecucion, tipo);

        /*
         * switch (algoritmoEjecucion) { case "EvM": resultado = EvM.EvMedia(tamPoblacion, tam,
         * evaluaciones, sol, rmin, rmax, probMutacion, probCruce, nAlg, semilla, observations,
         * tipo); System.out.println("Se ha ejecutado EvM"); break; case "EvBLX": resultado =
         * EvBLX.EvaBLX(tamPoblacion, tam, evaluaciones, sol, rmin, rmax, probMutacion, probCruce,
         * nAlg, alpha, semilla, observations, tipo); System.out.println("Se ha ejecutado EvBLX");
         * break; case "EvD": resultado = EvD.evDif(tamPoblacion, tam, evaluaciones, sol, rmin,
         * rmax, nAlg, semilla, observations, tipo); System.out.println("Se ha ejecutado EvD");
         * break; }
         */



        System.out.println(resultado);


    }
}

