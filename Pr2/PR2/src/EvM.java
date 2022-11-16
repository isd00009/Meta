import java.util.ArrayList;

public class EvM {

    public double EvMedia(int tamPoblacion, int tam, int evaluaciones, ArrayList<Double> sol,
            double rmin, double rmax, double probMutacion, double probCruce, int selector) {

        ArrayList<Cromosoma> cr = new ArrayList<Cromosoma>(tamPoblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            cr.add(new Cromosoma(tam));
        }

        ArrayList<ArrayList<Double>> nuevaG = new ArrayList<ArrayList<Double>>(tam);
        ArrayList<Double> costesH = new ArrayList<Double>(tamPoblacion);
        ArrayList<Double> costesH2 = new ArrayList<Double>(tamPoblacion);

        ArrayList<Integer> posicion = new ArrayList<Integer>(tamPoblacion);
        ArrayList<Double> mejorCromosoma = new ArrayList<Double>(tam);
        ArrayList<Double> mejorCromosomaGlobal = new ArrayList<Double>(tam);

        int peor, peorCosteHijo;
        int mejorCromosomaHijo = 0;

        double mejorCoste = Double.MAX_VALUE, mejorCosteHijo = Double.MAX_VALUE, mejorCosteGlobal;

        ArrayList<ArrayList<Double>> nuevaG2 = new ArrayList<ArrayList<Double>>(tam);

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
        int l = 0;

        while (cont < evaluaciones) {
            l++;
            for (int k = 0, i, j; k < tamPoblacion; k++) {
                i = (int) (Math.random() * tamPoblacion);
                while (i == (j = (int) (Math.random() * tamPoblacion)));
                posicion.add(k, ((cr.get(i).getCoste() < cr.get(j).getCoste()) ? i : j));
            }

            for (int i = 0; i < tamPoblacion; i++) {
                nuevaG.add(i, cr.get(posicion.get(i)).getCromosomas());
                costesH.add(i, cr.get(posicion.get(i)).getCoste());
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
                while (coste1 == (coste2 = (int) (Math.random() * tamPoblacion)));

                if (costesH.get(coste1) < costesH.get(coste2)) {
                    mejorCoste1 = costesH.get(coste1);
                    mejorCromosoma1 = nuevaG.get(coste1);
                    posMAnterior = coste1;
                } else {
                    mejorCoste1 = costesH.get(coste2);
                    mejorCromosoma1 = nuevaG.get(coste2);
                    posMAnterior = coste2;
                }

                while (posMAnterior == (coste3 = (int) (Math.random() * tamPoblacion)));
                while (posMAnterior == (coste4 = (int) (Math.random() * tamPoblacion)));

                if (costesH.get(coste3) < costesH.get(coste4)) {
                    mejorCoste2 = costesH.get(coste3);
                    mejorCromosoma2 = nuevaG.get(coste3);
                } else {
                    mejorCoste2 = costesH.get(coste4);
                    mejorCromosoma2 = nuevaG.get(coste4);
                }

                x = Math.random();
                if (x < probCruce) {
                    Funciones.cruceMedia(mejorCromosoma1, mejorCromosoma2, hijos, tam);
                    nuevaG2.add(i, hijos);
                    marcados.set(i, true);
                } else {
                    nuevaG2.add(i, mejorCromosoma1);
                    costesH2.add(i, mejorCoste1);
                }
            }

            nuevaG = nuevaG2;
            costesH = costesH2;

            // Mutamos

            for (int i = 0; i < tamPoblacion; i++) {
                boolean m = false;
                for (int j = 0; i < tam; j++) {
                    x = Math.random();
                    if (x < probMutacion) {
                        m = true;
                        double valor = rmin + (rmax - rmin) * Math.random();
                        Funciones.mutacion(nuevaG.get(i), j, valor);
                    }
                }
                if (m) {
                    marcados.set(i, true);
                }
            }

            for (int i = 0; i < tamPoblacion; i++) {
                if (marcados.get(i)) {
                    costesH.set(i, Funciones.CalcularCoste(nuevaG.get(i), selector));
                    cont++;
                }
                if (costesH.get(i) < mejorCosteHijo) {
                    mejorCosteHijo = costesH.get(i);
                    mejorCromosomaHijo = i;
                }
            }

            boolean aux = false;
            for (int i = 0; i < nuevaG.size() && !aux; i++) {
                if (nuevaG.get(i).equals(mejorCromosomaGlobal)) {
                    aux = true;
                }
            }

            if (!aux) {
                int pos1, pos2, pos3, pos4;
                pos1 = (int) (Math.random() * tamPoblacion);
                pos2 = (int) (Math.random() * tamPoblacion);
                pos3 = (int) (Math.random() * tamPoblacion);
                pos4 = (int) (Math.random() * tamPoblacion);

                while (pos1 == (pos2 = (int) (Math.random() * tamPoblacion)));
                while ((pos1 == pos2) && (pos1 == (pos3 = (int) (Math.random() * tamPoblacion)))
                        && pos2 == pos3);
                while ((pos1 == pos2) && (pos1 == pos3)
                        && (pos1 == (pos4 = (int) (Math.random() * tamPoblacion))) && (pos2 == pos3)
                        && (pos2 == pos4) && (pos3 == pos4));

                if (costesH.get(pos1) > costesH.get(pos2) && costesH.get(pos1) > costesH.get(pos3)
                        && costesH.get(pos1) > costesH.get(pos4)) {
                    peor = pos1;
                } else {
                    if (costesH.get(pos2) > costesH.get(pos1)
                            && costesH.get(pos2) > costesH.get(pos3)
                            && costesH.get(pos2) > costesH.get(pos4)) {
                        peor = pos2;
                    } else {
                        if (costesH.get(pos3) > costesH.get(pos1)
                                && costesH.get(pos3) > costesH.get(pos2)
                                && costesH.get(pos3) > costesH.get(pos4)) {
                            peor = pos3;
                        } else {
                            peor = pos4;
                        }
                    }
                }

                nuevaG.set(peor, mejorCromosoma);
                costesH.set(peor, mejorCoste);

                if (mejorCoste < mejorCosteHijo) {
                    mejorCosteHijo = mejorCoste;
                    nuevaG.set(mejorCromosomaHijo, mejorCromosoma);
                }
            }

            mejorCromosoma = nuevaG.get(mejorCromosomaHijo);
            mejorCoste = mejorCosteHijo;

            if (mejorCosteHijo < mejorCosteGlobal) {
                mejorCosteGlobal = mejorCosteHijo;
                mejorCromosomaGlobal = nuevaG.get(mejorCromosomaHijo);
            }

            // costes=costesH;
            // cromosomas=nuevag;

        }

        sol = mejorCromosomaGlobal;
        System.out.println("Total evaluaciones: " + cont);
        System.out.println("Total Iteraciones: " + l);
        return mejorCosteGlobal;
    }

}
