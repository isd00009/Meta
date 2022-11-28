import java.util.ArrayList;
import java.util.Random;

public class EvD {

    public static double evDif(int tamPoblacion, int tam, int evaluaciones, ArrayList<Double> sol,
            double rmin, double rmax, int selector, long semilla) {
        Random r = new Random(semilla);
        ArrayList<Cromosoma> cr = new ArrayList<Cromosoma>(tamPoblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            cr.add(new Cromosoma(tam));
        }

        ArrayList<Double> mejorCromosoma = new ArrayList<Double>(tam);
        ArrayList<Double> mejorCromosomaGlobal = new ArrayList<Double>(tam);
        double mejorCoste = Double.MAX_VALUE, mejorCosteGlobal;

        for (int i = 0; i < tamPoblacion; i++) {
            Funciones.cargaAleatoria(cr.get(i).getCromosomas(), tam, rmin, rmax, r);
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
            mejorCoste = Double.MAX_VALUE;

            ArrayList<Double> ran1, ran2, objetivo, padre, nuevo;
            nuevo = new ArrayList<Double>(tam);
            int r1, r2, k1, k2, k3;

            r1 = (int) (r.nextDouble() * tamPoblacion);
            r2 = (int) (r.nextDouble() * tamPoblacion);
            k1 = (int) (r.nextDouble() * tamPoblacion);
            k2 = (int) (r.nextDouble() * tamPoblacion);
            k3 = (int) (r.nextDouble() * tamPoblacion);

            for (int i = 0; i < tamPoblacion; i++) {
                padre = cr.get(i).getCromosomas();

                while (r1 != i && r2 != i) {
                    r1 = (int) (r.nextDouble() * tamPoblacion);

                    while (r1 == r2) {
                        r2 = (int) (r.nextDouble() * tamPoblacion);
                    }
                }

                ran1 = cr.get(r1).getCromosomas();
                ran2 = cr.get(r2).getCromosomas();

                while (k1 != i && k1 != r1 && k1 != r2 && k2 != i && k2 != r1 && k2 != r2 && k3 != i && k3 != r1
                        && k3 != r2) {
                    k1 = (int) (r.nextDouble() * tamPoblacion);

                    while(k1 == k2){
                        k2 = (int) (r.nextDouble() * tamPoblacion);
                    }
                    while(k1 == k3 || k2 == k3){
                        k3 = (int) (r.nextDouble() * tamPoblacion);
                    }
                }

                if(cr.get(k1).getCoste() < cr.get(k2).getCoste() && cr.get(k1).getCoste() < cr.get(k3).getCoste()){
                    objetivo = cr.get(k1).getCromosomas();
                }else{
                    if(cr.get(k2).getCoste() < cr.get(k3).getCoste()){
                        objetivo = cr.get(k2).getCromosomas();
                    }else{
                        objetivo = cr.get(k3).getCromosomas();
                    }
                }

                double F= r.nextDouble();

                for (int j=0; j<tam; j++){
                    double aletaorio= r.nextDouble();
                    if(aletaorio <= 0.5){
                        nuevo.add(j, padre.get(j) + F*(ran1.get(j)-ran2.get(j)));
                        if(nuevo. get(j) < rmin){
                            nuevo.set(j, rmin);
                        }else{
                            if(nuevo.get(j) > rmax){
                                nuevo.set(j, rmax);
                            }
                        }
                    }else{
                        nuevo.add(j, objetivo.get(j));
                    }
                }
            }

        }

        return 0;
    }
}
