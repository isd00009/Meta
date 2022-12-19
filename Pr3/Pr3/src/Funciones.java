import java.util.ArrayList;
import java.util.Random;

public class Funciones {

    public static void carga(ArrayList<Integer> s, Random r, int n, ArrayList<Boolean> marcados) {
        s.add(r.nextInt(n));
        marcados.set(s.get(0), true);
    }

    public static double coste(ArrayList<Integer> hormiga, ArrayList<ArrayList<Double>> distancias,
            int n) {
        double coste = 0;
        for (int i = 0; i < n - 1; i++) {
            coste += distancias.get(hormiga.get(i)).get(hormiga.get(i + 1));
        }
        coste += distancias.get(hormiga.get(0)).get(hormiga.get(n - 1));
        return coste;
    }

}
