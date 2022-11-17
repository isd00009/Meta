import java.util.ArrayList;

public class EvM {

    public double EvMedia(int tamPoblacion, int tam, int evaluaciones, ArrayList<Double> sol,
            double rmin, double rmax, double probMutacion, double probCruce, int selector) {

        ArrayList<Cromosoma> cr = new ArrayList<Cromosoma>(tamPoblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            cr.add(new Cromosoma(tam));
        }

        ArrayList<Cromosoma> gen1 = new ArrayList<Cromosoma>(tamPoblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            gen1.add(new Cromosoma(tam));
        }

        ArrayList<Cromosoma> gen2 = new ArrayList<Cromosoma>(tamPoblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            gen2.add(new Cromosoma(tam));
        }

        ArrayList<Integer> posicion = new ArrayList<Integer>(tamPoblacion);
        ArrayList<Double> mejorCromosoma = new ArrayList<Double>(tam);
        ArrayList<Double> mejorCromosomaGlobal = new ArrayList<Double>(tam);

        int peor, peorCosteHijo;
        int mejorCromosomaHijo = 0;

        double mejorCoste = Double.MAX_VALUE, mejorCosteHijo = Double.MAX_VALUE, mejorCosteGlobal;

        for (int i = 0; i < tamPoblacion; i++) {
            Funciones.cargaAleatoria(cr.get(i).getCromosomas(), tam, rmin, rmax);
            cr.get(i).setCoste(Funciones.CalcularCoste(cr.get(i).getCromosomas(), selector));

            if (cr.get(i).getCoste() < mejorCoste) {
                mejorCoste = cr.get(i).getCoste();
                mejorCromosoma = cr.get(i).getCromosomas();
            }
        }

        mejorCosteGlobal = mejorCoste;
        mejorCromosomaGlobal = mejorCromosoma;

        int cont = tamPoblacion;
        int it = 0;

        while (cont < evaluaciones) {
            it++;

            // torneo entre pares a modularizar
            for (int k = 0; k < tamPoblacion; k++) {
                int i, j;
                i = (int) (Math.random() * tamPoblacion);
                j = (int) (Math.random() * tamPoblacion);
                while (i == j) {
                    j = (int) (Math.random() * tamPoblacion);
                }

                if (cr.get(i).getCoste() < cr.get(j).getCoste()) {
                    posicion.add(k, i);
                } else {
                    posicion.add(k, j);
                }
            }

            for (int i = 0; i < tamPoblacion; i++) {
                gen1.get(i).setCromosomas(cr.get(posicion.get(i)).getCromosomas());
                gen1.get(i).setCoste(cr.get(posicion.get(i)).getCoste());
            }

            // Cruzamos
            ArrayList<Boolean> marcados = new ArrayList<Boolean>(tamPoblacion);
            for (int i = 0; i < tamPoblacion; i++) {
                marcados.add(i, false);
            }

            int coste1, coste2, coste3, coste4;
            double mejorCoste1, mejorCoste2;

            ArrayList<Double> mejorCromosoma1 = new ArrayList<Double>(tam);
            ArrayList<Double> mejorCromosoma2 = new ArrayList<Double>(tam);
            ArrayList<Double> hijos = new ArrayList<Double>(tam);
            double x;
            int posMAnterior;

            for (int i = 0; i < tamPoblacion; i++) {

                coste1 = (int) (Math.random() * tamPoblacion);
                coste2 = (int) (Math.random() * tamPoblacion);

                while (coste1 == coste2) {
                    coste2 = (int) (Math.random() * tamPoblacion);
                }

                if (gen1.get(coste1).getCoste() < gen1.get(coste2).getCoste()) {
                    mejorCoste1 = gen1.get(coste1).getCoste();
                    mejorCromosoma1 = gen1.get(coste1).getCromosomas();
                    posMAnterior = coste1;
                } else {
                    mejorCoste1 = gen1.get(coste2).getCoste();
                    mejorCromosoma1 = gen1.get(coste2).getCromosomas();
                    posMAnterior = coste2;
                }

                coste3 = (int) (Math.random() * tamPoblacion);
                while (posMAnterior == coste3) {
                    coste3 = (int) (Math.random() * tamPoblacion);
                }

                coste4 = (int) (Math.random() * tamPoblacion);
                while (posMAnterior == coste4) {
                    coste4 = (int) (Math.random() * tamPoblacion);
                }

                if (gen1.get(coste3).getCoste() < gen1.get(coste4).getCoste()) {
                    mejorCoste2 = gen1.get(coste3).getCoste();
                    mejorCromosoma2 = gen1.get(coste3).getCromosomas();
                } else {
                    mejorCoste2 = gen1.get(coste4).getCoste();
                    mejorCromosoma2 = gen1.get(coste4).getCromosomas();
                }

                x = Math.random();
                if (x < probCruce) {
                    Funciones.cruceMedia(mejorCromosoma1, mejorCromosoma2, hijos, tam);
                    gen2.get(i).setCromosomas(hijos);
                    marcados.set(i, true);
                } else {
                    gen2.get(i).setCromosomas(mejorCromosoma1);
                    gen2.get(i).setCoste(mejorCoste1);
                }
            }

            for (int i = 0; i < tamPoblacion; i++) {
                gen1.get(i).setCromosomas(gen2.get(i).getCromosomas());
                gen1.get(i).setCoste(gen2.get(i).getCoste());
            }

            // Mutamos

            for (int i = 0; i < tamPoblacion; i++) {
                boolean m = false;
                for (int j = 0; i < tam; j++) {
                    x = Math.random();
                    if (x < probMutacion) {
                        m = true;
                        double valor = rmin + (rmax - rmin) * Math.random();
                        Funciones.mutacion(gen1.get(i).getCromosomas(), j, valor);
                    }
                }
                if (m) {
                    marcados.set(i, true);
                }
            }

            for (int i = 0; i < tamPoblacion; i++) {
                if (marcados.get(i)) {
                    gen1.get(i).setCoste(Funciones.CalcularCoste(gen1.get(i).getCromosomas(), selector));
                    cont++;
                }

                if (gen1.get(i).getCoste() < mejorCosteHijo) {
                    mejorCosteHijo = gen1.get(i).getCoste();
                    mejorCromosomaHijo = i;
                }
            }

            boolean aux = false;
            for (int i = 0; i < gen1.size() && !aux; i++) {
                if (gen1.get(i).equals(mejorCromosomaGlobal)) {
                    aux = true;
                }
            }

            if (!aux) {
                int pos1, pos2, pos3, pos4;
                pos1 = (int) (Math.random() * tamPoblacion);
                pos2 = (int) (Math.random() * tamPoblacion);
                while (pos1 == pos2) {
                    pos2 = (int) (Math.random() * tamPoblacion);
                }

                pos3 = (int) (Math.random() * tamPoblacion);
                while ((pos1 == pos2) && (pos1 == pos3) && pos2 == pos3) {
                    pos3 = (int) (Math.random() * tamPoblacion);
                }

                pos4 = (int) (Math.random() * tamPoblacion);
                while ((pos1 == pos2) && (pos1 == pos3)
                        && (pos1 == (pos4) && (pos2 == pos3) && (pos2 == pos4) && (pos3 == pos4))) {
                    pos4 = (int) (Math.random() * tamPoblacion);
                }

                if (gen1.get(pos1).getCoste() > gen1.get(pos2).getCoste()
                        && gen1.get(pos1).getCoste() > gen1.get(pos3).getCoste()
                        && gen1.get(pos1).getCoste() > gen1.get(pos4).getCoste()) {
                    peor = pos1;
                } else {
                    if (gen1.get(pos2).getCoste() > gen1.get(pos1).getCoste()
                            && gen1.get(pos2).getCoste() > gen1.get(pos3).getCoste()
                            && gen1.get(pos2).getCoste() > gen1.get(pos4).getCoste()) {
                        peor = pos2;
                    } else {
                        if (gen1.get(pos3).getCoste() > gen1.get(pos1).getCoste()
                                && gen1.get(pos3).getCoste() > gen1.get(pos2).getCoste()
                                && gen1.get(pos3).getCoste() > gen1.get(pos4).getCoste()) {
                            peor = pos3;
                        } else {
                            peor = pos4;
                        }
                    }
                }

                gen1.get(peor).setCromosomas(mejorCromosoma);
                gen1.get(peor).setCoste(mejorCoste);

                if (mejorCoste < mejorCosteHijo) {
                    mejorCosteHijo = mejorCoste;
                    gen1.get(mejorCromosomaHijo).setCromosomas(mejorCromosoma);
                }
            }

            mejorCromosoma = gen1.get(mejorCromosomaHijo).getCromosomas();
            mejorCoste = mejorCosteHijo;

            if (mejorCosteHijo < mejorCosteGlobal) {
                mejorCosteGlobal = mejorCosteHijo;
                mejorCromosomaGlobal = gen1.get(mejorCromosomaHijo).getCromosomas();
            }

            for (int i = 0; i < tamPoblacion; i++) {
                cr.get(i).setCoste(gen1.get(i).getCoste());
                cr.get(i).setCromosomas(gen1.get(i).getCromosomas());
            }

        }

        sol = mejorCromosomaGlobal;
        System.out.println("Evaluaciones totales: " + cont);
        System.out.println("Iteraciones totales: " + it);
        return mejorCosteGlobal;
    }

}
