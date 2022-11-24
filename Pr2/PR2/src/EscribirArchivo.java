import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

public class EscribirArchivo {

    private String nombreFichero;
    private String nombreAlg;
    private int tam;
    private double rmin;
    private double rmax;
    private long semilla;
    private int evaluaciones;
    private int nAlg;
    private double aux;
    private ArrayList<Double> solActual;
    private int op;
    private int tTabu = 7;
    private int iteraciones;

    public EscribirArchivo(String nombreAlg, int tam, double rmin, double rmax, long semilla,
            int evaluaciones, int nAlg, int op) throws Exception {
        this.nombreAlg = nombreAlg;
        this.tam = tam;
        this.rmin = rmin;
        this.rmax = rmax;
        this.semilla = semilla;
        this.evaluaciones = evaluaciones;
        this.nAlg = nAlg;
        this.aux = 0;
        this.op = op;
        switch (op) {
            case 1:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "BLocal3" + ".log";
                break;
            case 2:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "BLocalK" + ".log";
                break;
            case 3:
                this.nombreFichero = "./Logs/" + nombreAlg + semilla + "BTabu" + ".log";
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
                aux = EvM.EvMedia(50, 10, evaluaciones, solActual, rmin, rmax, 0.01, 0.7, nAlg,
                        semilla);
                long endTime = System.currentTimeMillis();

                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Iteraciones: " + iteraciones);
                pw.println("Coste BLocal3: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
                break;
            case 2:
                long startTime2 = System.currentTimeMillis();
                // aux = BLocal.BlocalK(tam, evaluaciones, solActual, rmin, rmax, nAlg,
                // iteraciones);
                long endTime2 = System.currentTimeMillis();

                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Iteraciones: " + iteraciones);
                pw.println("Coste BLocalk: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime2 - startTime2) + " ms");
                break;
            case 3:
                long startTime3 = System.currentTimeMillis();
                // aux = MultiArranque.busquedaTabu(evaluaciones, tam, solActual, rmin, rmax, tTabu,
                // nAlg);
                long endTime3 = System.currentTimeMillis();
                pw.println("Nombre del algoritmo: " + nombreAlg);
                pw.println("Tamaño del problema: " + tam);
                pw.println("Rango de valores: [" + rmin + ", " + rmax + "]");
                pw.println("Semilla: " + semilla);
                pw.println("Coste BLocal3: " + aux);
                pw.println("Tiempo de ejecución: " + (endTime3 - startTime3) + " ms");
                break;
            default:
                break;
        }

        fichero.close();
    }


}
