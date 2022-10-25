import java.util.Vector;
import java.util.Random;

public class BLocal {

    private final static int nvecinos = 3;
    private final static double probabilidad = 0.3;
    private final static double intervaloBajo = 1 - 0.1;
    private final static double intervaloAlto = 1 + 0.1;

    public static double Blocal3(int tam, long evaluaciones, Vector<Double> SolActual, double rmin,
            double rmax, int selector) {

        Random rand = new Random();

        for (int i = 0; i < tam; i++) {
            SolActual.add(i, Math.floor(Math.random() * (rmax - rmin + 1) + rmin));
        }

        Aux.mostrarVector(SolActual);

        Vector<Double> vecino = new Vector<Double>(tam);
        Vector<Double> mejorVecino = new Vector<>(SolActual);
        double mejorCosteVecino;
        double mejorCoste = Funciones.CalcularCoste(SolActual, selector);
        int iteraciones = 0;
        boolean mejora = true;

        while (mejora && iteraciones < evaluaciones) {
            mejora = false;
            mejorCosteVecino = Integer.MAX_VALUE;
            for (int j = 1; j <= nvecinos; j++) {
                for (int k = 0; k < tam; k++) {
                    float uniforme = rand.nextFloat();
                    if (uniforme <= probabilidad) {
                        double inf, sup;
                        inf = SolActual.get(k) * intervaloBajo;
                        sup = SolActual.get(k) * intervaloAlto;
                        if (inf < rmin) {
                            inf = rmin;
                        }
                        if (sup > rmax) {
                            sup = rmax;
                        }
                        vecino.add(k, Math.floor(Math.random() * (sup - inf + 1) + sup));
                    } else
                        vecino.add(k, SolActual.get(k));

                }
                double costeVecino = Funciones.CalcularCoste(vecino, selector);
                if (costeVecino < mejorCosteVecino) {
                    mejorVecino = vecino;
                    mejorCosteVecino = costeVecino;
                }
            }
            if (mejorCosteVecino < mejorCoste) {
                SolActual = mejorVecino;
                mejorCoste = mejorCosteVecino;
                mejora = true;
                iteraciones++;
            }
        }

        System.out.println("Iteraciones:" + iteraciones);
        return mejorCoste;
    }

    public static double BlocalK(int tam, long evaluaciones, Vector<Double> SolActual, double rmin, double rmax, int selector) {

        Random rand = new Random();

        for (int i = 0; i < tam; i++) {
            SolActual.add(i, Math.floor(Math.random() * (rmax - rmin + 1) + rmin));
        }

        Aux.mostrarVector(SolActual);

        Vector<Double> vecino = new Vector<Double>(tam);
        Vector<Double> mejorVecino;
        mejorVecino = SolActual;
        double mejorCosteVecino;
        double mejorCoste = Funciones.CalcularCoste(SolActual, selector);
        int iteraciones = 0;
        boolean mejora = true;

        while (mejora && iteraciones < evaluaciones) {
            mejora = false;
            mejorCosteVecino = Integer.MAX_VALUE;
            int x = (int) Math.floor(Math.random() * (10 - 4 + 1) + 4);
            for (int j = 1; j <= x; j++) {
                for (int k = 0; k < tam; k++) {
                    float uniforme = rand.nextFloat();
                    if (uniforme <= probabilidad) {
                        double inf, sup;
                        inf = SolActual.get(k) * intervaloBajo;
                        sup = SolActual.get(k) * intervaloAlto;
                        if (inf < rmin) {
                            inf = rmin;
                        }
                        if (sup > rmax) {
                            sup = rmax;
                        }
                        vecino.add(k, Math.floor(Math.random() * (sup - inf + 1) + sup));
                    } else
                        vecino.add(k, SolActual.get(k));

                }
                double costeVecino = Funciones.CalcularCoste(vecino, selector);
                if (costeVecino < mejorCosteVecino) {
                    mejorVecino = vecino;
                    mejorCosteVecino = costeVecino;
                }
            }
            if (mejorCosteVecino < mejorCoste) {
                SolActual = mejorVecino;
                mejorCoste = mejorCosteVecino;
                mejora = true;
                iteraciones++;
            }
        }

        System.out.println("Iteraciones:" + iteraciones);
        return mejorCoste;
    }

}
