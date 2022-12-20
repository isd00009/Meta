import java.util.ArrayList;

public class Pr3 {
    public static void main(String[] args) throws Exception {

        ArrayList<ArrayList<Double>> distancias;
        ArrayList<Integer> sol;
        int tam;
        String ficheroProb = "";
        int n, m;
        int iteraciones = 0, poblacion = 0, semilla = 0, alfa = 0, beta = 0, ini;
        double greedy = 0.0, q0 = 0.0, p = 0.0, fi = 0.0, delta;

        LeerArchivo lector = new LeerArchivo();
        lector.leerConfig();

        semilla = lector.getSemilla();
        iteraciones = lector.getIteraciones();
        poblacion = lector.getPoblacion();
        greedy = lector.getGreedy();
        alfa = lector.getAlfa();
        beta = lector.getBeta();
        q0 = lector.getQ0();
        p = lector.getP();
        fi = lector.getFi();
        ficheroProb = lector.getFicheroALeer();

        lector.leerFich(ficheroProb);
        distancias = lector.getDist();
        n = lector.getN();

        System.out.println("Semilla: " + semilla);
        System.out.println("Iteraciones: " + iteraciones);
        System.out.println("Poblacion: " + poblacion);
        System.out.println("Greedy: " + greedy);
        System.out.println("Alfa: " + alfa);
        System.out.println("Beta: " + beta);
        System.out.println("Q0: " + q0);
        System.out.println("P: " + p);
        System.out.println("Fi: " + fi);
        System.out.println("Fichero a leer: " + ficheroProb);
        System.out.println("N: " + n);
        // System.out.println("Distancias: " + distancias);
        sol = new ArrayList<Integer>(n);

        long startTime = System.currentTimeMillis();
        SCH.Hormigas(distancias, n, sol, iteraciones, poblacion, greedy, alfa, beta, q0, p, fi,
                semilla);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Tiempo: " + totalTime + " ms");



    }
}
