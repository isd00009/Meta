import java.util.ArrayList;

public class Hormiga {
    private ArrayList<ArrayList<Integer>> hormigas;
    private ArrayList<Double> costes;

    public Hormiga(int tam) {
        hormigas = new ArrayList<ArrayList<Integer>>();
        costes = new ArrayList<Double>();
        for (int i = 0; i < tam; i++) {
            hormigas.add(new ArrayList<Integer>());
            costes.add(0.0);
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
