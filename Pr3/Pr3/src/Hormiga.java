import java.util.ArrayList;

public class Hormiga {
    private ArrayList<ArrayList<Integer>> hormigas;
    private ArrayList<Double> costes;

    public Hormiga(int tam, int n) {
        hormigas = new ArrayList<ArrayList<Integer>>(tam);
        costes = new ArrayList<Double>(n);
        for (int i = 0; i < tam; i++) {
            ArrayList<Integer> aux = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                aux.add(0);
                costes.add(0.0);
            }
            hormigas.add(aux);
        }
    }

    public void setHormiga(int i, ArrayList<Integer> s) {
        hormigas.set(i, s);
    }

    public void setCoste(int i, double c) {
        costes.set(i, c);
    }

    public ArrayList<Integer> getHormiga(int i) {
        return hormigas.get(i);
    }

    public ArrayList<ArrayList<Integer>> getHormigas() {
        return hormigas;
    }

    public ArrayList<Double> getCostes() {
        return costes;
    }

    public double getCoste(int i) {
        return costes.get(i);
    }
}
