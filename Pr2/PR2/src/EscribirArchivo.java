import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscribirArchivo {

    private String nombreFichero;
    private String nombreAlg;
    private String algoritmoEj;
    private int tipo;
    private int tam;
    private double rmin;
    private double rmax;
    private long semilla;
    private int evaluaciones;
    private int nAlg;
    private double aux;
    private ArrayList<Double> solActual;
    private int op;
    private double probMutacion = 0.01;
    private double probCruce = 0.7;
    private double alpha = 0.5;
    private int tamPoblacion = 50;
    ArrayList<ArrayList<Double>> observations;

    public EscribirArchivo(String nombreAlg, int tam, double rmin, double rmax, long semilla,
            int evaluaciones, int nAlg, int op, String algoritmoEj, int tipo,
            ArrayList<ArrayList<Double>> observations) throws Exception {
        this.nombreAlg = nombreAlg;
        this.tam = tam;
        this.rmin = rmin;
        this.rmax = rmax;
        this.semilla = semilla;
        this.evaluaciones = evaluaciones;
        this.nAlg = nAlg;
        this.aux = 0;
        this.op = op;
        this.algoritmoEj = algoritmoEj;
        this.tipo = tipo;
        this.observations = observations;

        switch (op) {
            case 1:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "EvM" + ".log";
                break;
            case 2:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "EvBLX" + ".log";
                break;
            case 3:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "EvD" + ".log";
                break;
            case 4:
                this.nombreFichero = "./Logs/" + algoritmoEj + semilla + "MAPE" + ".log";
                break;
            case 5:
                this.nombreFichero = "./Logs/" + algoritmoEj + semilla + "RMSE" + ".log";
                break;
            default:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + ".log";
                break;
        }

        this.solActual = new ArrayList<Double>(tam);
        escribir();
    }

    public void escribir() throws Exception {
        FileWriter fichero = new FileWriter(nombreFichero, true);
        PrintWriter pw = new PrintWriter(fichero);

        switch (op) {
            case 1:
                long startTime = System.currentTimeMillis();
                aux = EvM.EvMedia(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                        probMutacion, probCruce, nAlg, semilla, observations, tipo);
                long endTime = System.currentTimeMillis();

                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Coste EvM: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
                break;
            case 2:
                long startTime2 = System.currentTimeMillis();
                aux = EvBLX.EvaBLX(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                        probMutacion, probCruce, nAlg, alpha, semilla, observations, tipo);
                long endTime2 = System.currentTimeMillis();

                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Coste EvBLX: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime2 - startTime2) + " ms");
                break;
            case 3:
                long startTime3 = System.currentTimeMillis();
                aux = EvD.evDif(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax, nAlg,
                        semilla, observations, tipo);
                long endTime3 = System.currentTimeMillis();
                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Coste EvD: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime3 - startTime3) + " ms");
                break;
            case 4:
                switch (algoritmoEj) {
                    case "EvM":
                        long startTime4 = System.currentTimeMillis();
                        aux = EvM.EvMedia(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                probMutacion, probCruce, nAlg, semilla, observations, tipo);
                        long endTime4 = System.currentTimeMillis();
                        pw.println("Nombre del algoritmo: " + algoritmoEj);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvM-MAPE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime4 - startTime4) + " ms");
                        break;
                    case "EvBLX":
                        long startTime5 = System.currentTimeMillis();
                        aux = EvBLX.EvaBLX(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                probMutacion, probCruce, nAlg, alpha, semilla, observations, tipo);
                        long endTime5 = System.currentTimeMillis();

                        pw.println("Nombre del algoritmo: " + nombreAlg);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvBLX-MAPE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime5 - startTime5) + " ms");
                        break;
                    case "EvD":
                        long startTime6 = System.currentTimeMillis();
                        aux = EvD.evDif(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                nAlg, semilla, observations, tipo);
                        long endTime6 = System.currentTimeMillis();
                        pw.println("Nombre del algoritmo: " + nombreAlg);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvD-MAPE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime6 - startTime6) + " ms");
                        break;
                }
                break;
            case 5:
                switch (algoritmoEj) {
                    case "EvM":
                        long startTime7 = System.currentTimeMillis();
                        aux = EvM.EvMedia(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                probMutacion, probCruce, nAlg, semilla, observations, tipo);
                        long endTime7 = System.currentTimeMillis();
                        pw.println("Nombre del algoritmo: " + algoritmoEj);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvM-RMSE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime7 - startTime7) + " ms");
                        break;
                    case "EvBLX":
                        long startTime8 = System.currentTimeMillis();
                        aux = EvBLX.EvaBLX(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                probMutacion, probCruce, nAlg, alpha, semilla, observations, tipo);
                        long endTime8 = System.currentTimeMillis();

                        pw.println("Nombre del algoritmo: " + nombreAlg);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvBLX-RMSE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime8 - startTime8) + " ms");
                        break;
                    case "EvD":
                        long startTime9 = System.currentTimeMillis();
                        aux = EvD.evDif(tamPoblacion, tam, evaluaciones, solActual, rmin, rmax,
                                nAlg, semilla, observations, tipo);
                        long endTime9 = System.currentTimeMillis();
                        pw.println("Nombre del algoritmo: " + nombreAlg);
                        pw.println("Tamaño del problema: " + tam);
                        pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                        pw.println("Semilla: " + semilla);
                        pw.println("Coste EvD-RMSE: " + aux);
                        pw.println("Tiempo de ejecución: " + (endTime9 - startTime9) + " ms");
                        break;
                }
                break;
            default:
                break;
        }

        fichero.close();
    }


}
