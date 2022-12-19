import java.util.ArrayList;
import java.util.Random;

public class Algoritmo {

    public static void Hormigas(ArrayList<ArrayList<Double>> distancias, int n,
            ArrayList<Integer> sol, int iteraciones, int poblacion, double greedy, int alfa,
            int beta, double q0, double p, double fi, int semilla) {

        Random r = new Random(semilla);
        Hormiga h = new Hormiga(poblacion);
        ArrayList<ArrayList<Double>> feromona;
        ArrayList<ArrayList<Double>> heuristica;
        ArrayList<ArrayList<Boolean>> marcados;
        ArrayList<Integer> mejorHormigaAct = new ArrayList<Integer>(n);
        double mejorCosteGlobal = Double.MAX_VALUE, mejorCosteActual = Double.MAX_VALUE;
        double fInicial;
        int cont = 0;
        double tiempoTotal = 0;

        feromona = new ArrayList<ArrayList<Double>>(n);
        heuristica = new ArrayList<ArrayList<Double>>(n);
        marcados = new ArrayList<ArrayList<Boolean>>(n);

        fInicial = (double) (1.0 / (poblacion * greedy));

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    feromona.get(i).set(j, fInicial);
                    feromona.get(j).set(i, fInicial);
                    heuristica.get(i).set(j, (double) (1.0 / distancias.get(i).get(j)));
                    heuristica.get(j).set(i, (double) (1.0 / distancias.get(i).get(j)));
                }
            }
        }

        double tiempoInicio;

        while (cont < iteraciones && tiempoTotal < 600000) {

            tiempoInicio = System.currentTimeMillis();

            for (int i = 0; i < poblacion; i++) {
                Funciones.carga(h.getHormiga(i), r, n, marcados.get(i));
            }

            char c;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < poblacion; j++) {
                    ArrayList<Double> ferxHeu = new ArrayList<Double>(n);
                    for (int k = 0; k < n; k++) {
                        if (!marcados.get(j).get(k)) {
                            ferxHeu.add(Math.pow(feromona.get(h.getHormiga(j).get(i - 1)).get(k),
                                    alfa)
                                    * Math.pow(heuristica.get(h.getHormiga(j).get(i - 1)).get(k),
                                            beta));
                        }
                    }

                    double denominador = 0.0;
                    double argMax = 0.0;
                    int posArgMax = 0;

                    for (int k = 0; k < n; k++) {
                        if (!marcados.get(j).get(k)) {
                            denominador += ferxHeu.get(k);
                            if (ferxHeu.get(k) > argMax) {
                                argMax = ferxHeu.get(k);
                                posArgMax = k;
                            }
                        }
                    }

                    int elegido = 0;
                    ArrayList<Double> prob = new ArrayList<Double>(n);
                    double q = r.nextDouble();

                    if (q0 > q) {
                        elegido = posArgMax;
                    } else {
                        for (int k = 0; k < n; k++) {
                            if (!marcados.get(j).get(k)) {
                                prob.add(ferxHeu.get(k) / denominador);
                            }
                        }

                        double uniforme = r.nextDouble();
                        double suma = 0.0;
                        for (int k = 0; k < n; k++) {
                            if (!marcados.get(j).get(k)) {
                                suma += prob.get(k);
                                if (suma >= uniforme) {
                                    elegido = k;
                                    break;
                                }
                            }
                        }
                    }

                    h.getHormiga(j).set(i, elegido);
                    marcados.get(j).set(elegido, true);
                }
                for (int j = 0; j < poblacion; j++) {
                    feromona.get(h.getHormiga(j).get(i - 1)).set(h.getHormiga(j).get(i), ((1 - fi)
                            * feromona.get(h.getHormiga(j).get(i - 1)).get(h.getHormiga(j).get(i)))
                            + (fi * fInicial));
                    feromona.get(h.getHormiga(j).get(i)).set(h.getHormiga(j).get(i - 1),
                            feromona.get(h.getHormiga(j).get(i - 1)).get(h.getHormiga(j).get(i)));

                }
            }

            for (int i = 0; i < poblacion; i++) {
                double coste = Funciones.coste(h.getHormiga(i), distancias, n);
                if (coste < mejorCosteActual) {
                    mejorCosteActual = coste;
                    mejorHormigaAct = h.getHormiga(i);
                }
            }

            if (mejorCosteActual < mejorCosteGlobal) {
                mejorCosteGlobal = mejorCosteActual;
                sol = mejorHormigaAct;
            }

            double deltaMejor = 1.0 / mejorCosteActual;
            for (int i = 0; i < n - 1; i++) {
                feromona.get(mejorHormigaAct.get(i)).set(mejorHormigaAct.get(i + 1),
                        (p * deltaMejor));
                feromona.get(mejorHormigaAct.get(i + 1)).set(mejorHormigaAct.get(i),
                        feromona.get(mejorHormigaAct.get(i)).get(mejorHormigaAct.get(i + 1)));
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        feromona.get(i).set(j, (1 - p) * feromona.get(i).get(j));
                    }
                }
            }

            for (int i = 0; i < poblacion; i++) {
                h.getHormiga(i).clear();
            }
            h.getHormigas().clear();
            h.getCostes().clear();
            h = new Hormiga(poblacion);
            for (int i = 0; i < poblacion; i++) {
                marcados.get(i).clear();
            }
            marcados.clear();
            marcados = new ArrayList<ArrayList<Boolean>>(poblacion);

            cont++;

            if (cont % 100 == 0) {
                System.out.println("Iteracion: " + cont);
                System.out.println("Mejor coste global: " + mejorCosteGlobal);
            }

            double tiempoFinal = System.currentTimeMillis();
            tiempoTotal += (tiempoFinal - tiempoInicio);
        }
        System.out.println("Total iteraciones: " + cont);
    }
}
